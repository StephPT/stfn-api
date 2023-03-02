package com.steph.api.services.portfolio.util;

import org.imgscalr.Scalr;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

@Service
public class CompressionService {

    @Value("${image.save.location}")
    private String saveLocation;

    public File compress(File file) {
        File result = new File(saveLocation + "final-" + file.getName());
        try {
            BufferedImage bufferedImage = ImageIO.read(file);
            BufferedImage outputImage = Scalr.resize(bufferedImage, (bufferedImage.getWidth() + bufferedImage.getHeight()) / 2);
            ImageIO.write(outputImage, "jpg", result);
            outputImage.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

}
