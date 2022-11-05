package com.castillo.qrcode.draw;

import com.castillo.qrcode.utils.FileFormat;
import com.castillo.qrcode.utils.IOUtils;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.image.BufferedImage;

import static org.junit.jupiter.api.Assertions.*;

class LogoPainterTest {

    @Test
    void draw() {
        BufferedImage image = new BufferedImage(100,100, BufferedImage.TYPE_INT_ARGB);
        Graphics graphics= image.getGraphics();
        graphics.setColor(Color.RED);
        graphics.drawRect(0,0,100,100);
        graphics.fillRect(0,0,100,100);

        BufferedImage logo = new BufferedImage(100,100, BufferedImage.TYPE_INT_RGB);
        graphics= logo.getGraphics();
        graphics.setColor(Color.WHITE);
        graphics.drawRect(0,0,100,100);
        graphics.fillRect(0,0,100,100);

        BufferedImage result = new LogoPainter(logo, null, null).draw(image);
        int x = 36;
        int y = 36;

        IOUtils.save(result, "test.png", FileFormat.PNG);
        System.out.println(result.getRGB(x,y));
        System.out.println(result.getRGB(x++,y++));
        System.out.println(result.getRGB(x++,y++));
        assertEquals(Color.WHITE.getRGB(), result.getRGB(x+1,y+1));
//        assertEquals(Color.RED.getRGB(), result.getRGB(37,37));
//        assertEquals(Color.RED.getRGB(), result.getRGB(x+56,y+56));


    }
}