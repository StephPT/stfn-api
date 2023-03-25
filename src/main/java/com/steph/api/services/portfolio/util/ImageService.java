package com.steph.api.services.portfolio.util;

import com.steph.api.services.portfolio.data.PhotoRepository;
import com.steph.api.services.portfolio.entities.Attributes;
import com.steph.api.services.portfolio.entities.Gallery;
import com.steph.api.services.portfolio.entities.Photo;
import org.apache.commons.imaging.ImageReadException;
import org.apache.commons.imaging.Imaging;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ImageService {

    private static final Logger LOG = LoggerFactory.getLogger(ImageService.class);

    private static final String ISO_KEY = "Unknown Tag (0x8832)";

    @Autowired
    private PhotoRepository photoRepository;

    @Value("${image.save.location}")
    private String tempDir;

    public File createFile(MultipartFile image) {
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

    public List<Attributes> getAttributes(File tempFile, Photo photo) {
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

    public Photo createPhoto(Gallery gallery, File tempFile) {
        Photo photo = new Photo(UUID.randomUUID().toString(), gallery.getUuid(), tempFile.getName());
        photo.setUploaded(Date.valueOf(LocalDate.now()));
        photo.setFilePath(tempFile.getName());
        gallery.getPhotos().add(photo);
        return photo;
    }

    public String getImagePath(String uuid) {
        return tempDir + photoRepository.getReferenceById(uuid).getFilePath();
    }

}
