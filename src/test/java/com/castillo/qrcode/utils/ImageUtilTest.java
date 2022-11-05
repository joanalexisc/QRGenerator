package com.castillo.qrcode.utils;

import com.castillo.qrcode.draw.ColorReplacement;
import com.castillo.qrcode.draw.LogoPainter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class ImageUtilTest {
    BufferedImage image;

    @BeforeEach
    void setup(){
        image = new BufferedImage(100,100, BufferedImage.TYPE_INT_ARGB);
        Graphics graphics= image.getGraphics();
        graphics.setColor(Color.RED);
        graphics.drawRect(0,0,100,100);
        graphics.fillRect(0,0,100,100);
    }


    @Test
    void replaceColor() {
        assertEquals(Color.RED.getRGB(), image.getRGB(2,2));
        BufferedImage resultImage = ImageUtil.replaceColor(image, ColorReplacement.of(Color.red, Color.green));
        Random rand = new Random();
        assertEquals(Color.green.getRGB(), resultImage.getRGB(rand.nextInt(100),rand.nextInt(100)));
    }

    @Test
    void drawOver() {

        BufferedImage logo = new BufferedImage(50,50, BufferedImage.TYPE_INT_RGB);
        Graphics graphics = logo.getGraphics();
        graphics.setColor(Color.WHITE);
        graphics.drawRect(0,0,50,50);
        graphics.fillRect(0,0,50,50);

        BufferedImage result = ImageUtil.drawOver(image, logo);

        assertEquals(Color.white.getRGB(), result.getRGB(25,25));
        assertEquals(Color.white.getRGB(), result.getRGB(25,74));
        assertEquals(Color.white.getRGB(), result.getRGB(74,74));
        assertEquals(Color.white.getRGB(), result.getRGB(74,25));

        assertEquals(Color.red.getRGB(), result.getRGB(24,24));
        assertEquals(Color.red.getRGB(), result.getRGB(25,75));
        assertEquals(Color.red.getRGB(), result.getRGB(75,75));
        assertEquals(Color.red.getRGB(), result.getRGB(75,24));

    }

    @Test
    void resizeImage() {
        BufferedImage result = ImageUtil.resizeImage(image, image.getWidth(), image.getHeight(), 4);
        assertEquals(25,result.getWidth());
        assertEquals(25,result.getHeight());
    }

    @Test
    void imageTransparency() {
        assertEquals(255, new Color(image.getRGB(10,10),true).getAlpha());
        BufferedImage result = ImageUtil.imageTransparency(image, 0.25f);
        assertEquals(64, new Color(result.getRGB(10,10),true).getAlpha());
    }
}