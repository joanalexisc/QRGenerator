package com.castillo.qrcode.draw;

import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.image.BufferedImage;

import static org.junit.jupiter.api.Assertions.*;

class BackgroundPainterTest {
    @Test
    void draw() {
        BufferedImage bufferedImage = new BufferedImage(200,200,BufferedImage.TYPE_INT_RGB);
        Graphics g = bufferedImage.getGraphics();
        g.drawRect(0,0,200,200);
        g.setColor(Color.WHITE);
        g.fillRect(0,0,200,200);
        BackgroundPainter backGroundPainter = new BackgroundPainter(Color.BLUE);
        assertEquals(Color.WHITE.getRGB(), bufferedImage.getRGB(40, 40));
        assertEquals(Color.BLUE.getRGB(), backGroundPainter.draw(bufferedImage).getRGB(40, 40));
    }
}