package com.castillo.qrcode;

import com.castillo.qrcode.utils.IOUtils;
import com.castillo.qrcode.utils.QRUtils;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.image.BufferedImage;

import static org.junit.jupiter.api.Assertions.*;

class QRBuilderTest {
    @Test
    void throwsNullPointer(){
        assertThrows(NullPointerException.class, ()-> QRBuilder.build(builder -> builder.withData(null)), "the qr code require some input data");
        assertThrows(NullPointerException.class, ()-> QRBuilder.build(builder -> builder.withData("test")), "you need to specify the size of the code in pixels");
    }

    @Test
    void generate() {
        final String message = "this is a simple test";
        QRWrapper qr = QRBuilder.build(builder ->
                builder.withData(message)
                        .withSizeInPixels(100));

        BufferedImage image = qr.toImage();

        assertEquals(100, image.getHeight());
        assertEquals(100, image.getWidth());
        assertEquals(Color.WHITE.getRGB(), image.getRGB(1,1));
        assertEquals(Color.black.getRGB(), image.getRGB(30,30));
        assertEquals(message, QRUtils.get().read(image));
    }

    @Test
    void customize() {
        final String message = "this is a simple test";
         QRWrapper qr = QRBuilder.build(builder ->
                builder.withData(message)
                        .withSizeInPixels(250)
                        .withColor(Color.red)
                        .withBackgroundColor(Color.orange)
                        .withLogo(IOUtils.getImage("src/test/resources/logo.png"))
        );
        BufferedImage image = qr.toImage();
        assertEquals(250, image.getHeight());
        assertEquals(250, image.getWidth());
        assertEquals(Color.orange.getRGB(), image.getRGB(1,1));
        assertEquals(Color.red.getRGB(), image.getRGB(50,50));
        assertEquals(message, QRUtils.get().read(image));
        qr.save("qr2.png");
    }
}