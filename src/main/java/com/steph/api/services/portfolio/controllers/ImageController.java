package com.steph.api.services.portfolio.controllers;

import com.steph.api.services.portfolio.data.GalleryRepository;
import com.steph.api.services.portfolio.data.PhotoRepository;
import com.steph.api.services.portfolio.entities.Attributes;
import com.steph.api.services.portfolio.entities.Gallery;
import com.steph.api.services.portfolio.entities.Photo;
import com.steph.api.services.portfolio.util.CompressionService;
import org.apache.commons.imaging.ImageReadException;
import org.apache.commons.imaging.Imaging;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin
public class ImageController {

    private static final Logger LOG = LoggerFactory.getLogger(ImageController.class);

    private static final List<String> contentTypes = Arrays.asList("image/png", "image/jpeg", "image/gif");

    private static final String ISO_KEY = "Unknown Tag (0x8832)";

    @Autowired
    private GalleryRepository repository;

    @Autowired
    private PhotoRepository photoRepository;

    @Autowired
    private CompressionService compressionService;

    @Value("${image.save.location}")
    private String tempDir;

    @RequestMapping(value = "/portfolio/send", method = RequestMethod.POST)
    public Gallery send(@RequestParam("image") MultipartFile[] images, @RequestParam("name") String name) {
        Gallery gallery = new Gallery(UUID.randomUUID().toString(), name);
        List.of(images).forEach(image -> {
            if (contentTypes.contains(image.getContentType())) {
                File tempFile = createFile(image);
                getAttributes(tempFile, createPhoto(gallery, compressionService.compress(tempFile)));
                repository.saveAndFlush(gallery);
            }
        });
        return gallery;
    }

    @RequestMapping(value = "/portfolio/get/{uuid}", method = RequestMethod.GET)
    public Gallery get(@PathVariable("uuid") String uuid) {
        return repository.getOne(uuid);
    }

    @RequestMapping(value = "/portfolio/image/get/{uuid}", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> getImage(@PathVariable("uuid") String uuid) throws IOException {
        return new ResponseEntity<>(IOUtils.toByteArray(new FileInputStream(tempDir + photoRepository.getOne(uuid).getFilePath())),
                                                        new HttpHeaders(),
                                                        HttpStatus.OK);
    }

    private File createFile(MultipartFile image) {
        File file = new File(tempDir + image.getOriginalFilename());
        OutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(file);
            outputStream.write(image.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        IOUtils.closeQuietly(outputStream);
        return file;
    }

    private List<Attributes> getAttributes(File tempFile, Photo photo) {
        List<Attributes> result = new ArrayList<>();
        try {
            Imaging.getMetadata(tempFile).getItems().forEach(data -> {
                Attributes attributes = new Attributes();
                attributes.setUuid(UUID.randomUUID().toString());
                attributes.setPhotoId(photo.getUuid());
                String[] value = data.toString().split(":");
                if (ISO_KEY.equals(value[0])) {
                    value[0] = "ISO";
                }
                if (value[1].charAt(0) == ' ') {
                    value[1] = value[1].substring(1);
                }
                value[1] = value[1].replaceAll("'", "");
                attributes.setAttribute(value[0]);
                attributes.setValue(value[1]);
                photo.getAttributes().add(attributes);
                result.add(attributes);
            });
            if (!tempFile.delete()) {
                LOG.warn("Failed to delete tempFile in " + tempFile.getAbsolutePath());
                tempFile.deleteOnExit();
            }
        } catch (ImageReadException | IOException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    private Photo createPhoto(Gallery gallery, File tempFile) {
        Photo photo = new Photo(UUID.randomUUID().toString(), gallery.getUuid(), tempFile.getName());
        photo.setUploaded(Date.valueOf(LocalDate.now()));
        photo.setFilePath(tempFile.getName());
        gallery.getPhotos().add(photo);
        return photo;
    }

}
