package com.castillo.qrcode.draw;

import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.image.BufferedImage;

import static org.junit.jupiter.api.Assertions.*;

class CodePainterTest {

    @Test
    void draw() {
        BufferedImage bufferedImage = new BufferedImage(200,200,BufferedImage.TYPE_INT_RGB);
        Graphics g = bufferedImage.getGraphics();
        g.drawRect(0,0,200,200);
        g.setColor(Color.BLACK);
        g.fillRect(0,0,200,200);
        CodePainter backGroundPainter = new CodePainter(Color.RED);
        assertEquals(Color.BLACK.getRGB(), bufferedImage.getRGB(40, 40));
        assertEquals(Color.RED.getRGB(), backGroundPainter.draw(bufferedImage).getRGB(40, 40));
    }

}