package com.castillo.qrcode.utils;

import com.castillo.qrcode.exceptions.ReadFileException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class IOUtilsTest {
    @Test
    public void whenThrowReadFileException_themAssertionSucceed(){
        assertThrows(ReadFileException.class, ()-> IOUtils.getImage("file_no_exists.png"));
    }
    @Test
    public void loadImage(){
        assertNotNull(IOUtils.getImage("src/test/resources/logo.png"));
    }

    @Test
    public void saveImage(@TempDir Path tempDir){
        BufferedImage image = IOUtils.getImage("src/test/resources/logo.png");
        String fileName = tempDir.toString().concat("newFile.png");
        IOUtils.save(image,fileName, FileFormat.PNG);
        assertTrue(new File(fileName).exists());
    }
}