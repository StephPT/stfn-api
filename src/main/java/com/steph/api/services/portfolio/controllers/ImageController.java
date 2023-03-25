package com.steph.api.services.portfolio.controllers;

import com.steph.api.services.portfolio.data.GalleryRepository;
import com.steph.api.services.portfolio.entities.Gallery;
import com.steph.api.services.portfolio.util.CompressionService;
import com.steph.api.services.portfolio.util.ImageService;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin
public class ImageController {

    private static final Logger LOG = LoggerFactory.getLogger(ImageController.class);

    private static final List<String> contentTypes = Arrays.asList("image/png", "image/jpeg", "image/gif");

    @Autowired
    private GalleryRepository repository;

    @Autowired
    private CompressionService compressionService;

    @Autowired
    private ImageService imageService;

    @RequestMapping(value = "/portfolio/send", method = RequestMethod.POST)
    public Gallery send(@RequestParam("image") MultipartFile[] images, @RequestParam("name") String name) {
        Gallery gallery = new Gallery(UUID.randomUUID().toString(), name);
        long startTime = System.currentTimeMillis();
        List.of(images).forEach(image -> {
            LOG.info("Processing file: " + image.getOriginalFilename());
            if (contentTypes.contains(image.getContentType())) {
                File tempFile = imageService.createFile(image);
                imageService.getAttributes(tempFile, imageService.createPhoto(gallery, compressionService.compress(tempFile)));
                repository.saveAndFlush(gallery);
            } else {
                LOG.warn("Image type " + image.getContentType() + " failed upload request");
            }
        });
        LOG.info("Processing Completed. Took " + Float.toString((System.currentTimeMillis() - startTime) / 1000) + " seconds");
        return gallery;
    }

    @RequestMapping(value = "/portfolio/get/{uuid}", method = RequestMethod.GET)
    public Gallery get(@PathVariable("uuid") String uuid) {
        return repository.getReferenceById(uuid);
    }

    @RequestMapping(value = "/portfolio/image/get/{uuid}", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> getImage(@PathVariable("uuid") String uuid) throws IOException {
        return new ResponseEntity<>(IOUtils.toByteArray(new FileInputStream(imageService.getImagePath(uuid))),
                                                        new HttpHeaders(),
                                                        HttpStatus.OK);
    }

    @RequestMapping(value = "/portfolio/image/galleries", method = RequestMethod.GET)
    public List<Gallery> getGalleries() {
        return repository.findAll();
    }

    @RequestMapping(value = "/portfolio/image/delete/{uuid}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("uuid") String uuid) {
        repository.delete(repository.getReferenceById(uuid));
    }
}
