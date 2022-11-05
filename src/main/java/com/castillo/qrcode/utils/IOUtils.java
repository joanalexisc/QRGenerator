package com.castillo.qrcode.utils;

import com.castillo.qrcode.exceptions.CreateFileException;
import com.castillo.qrcode.exceptions.ReadFileException;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static java.util.Objects.requireNonNull;

public class IOUtils {
    public static BufferedImage getImage(String imagePath) {
        requireNonNull(imagePath, "The image path is required");
        try {
            return ImageIO.read(new File(imagePath));
        } catch (IOException e) {
            throw new ReadFileException("Couldn't read the file: ".concat(imagePath), e);
        }
    }

    public static File save(BufferedImage image, String fileName, FileFormat fileFormat) {
        requireNonNull(fileName, "The image can't be null");
        requireNonNull(fileName, "The fileName can't be null");
        requireNonNull(fileFormat, "The file format can't be null");

        try {
            File imageFile = new File(fileName);
            ImageIO.write(image, fileFormat.toString(), imageFile);
            return imageFile;
        } catch (IOException e) {
            throw new CreateFileException("Could not create file", e);
        }
    }
}
