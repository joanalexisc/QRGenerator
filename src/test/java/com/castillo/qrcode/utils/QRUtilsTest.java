package com.castillo.qrcode.utils;

import com.castillo.qrcode.exceptions.ReadQRException;
import org.junit.jupiter.api.Test;

import java.awt.image.BufferedImage;

import static org.junit.jupiter.api.Assertions.*;

class QRUtilsTest {
    static final String EXPECTED_RESULT = "this was generated with an external tool";
    private static final String EXPECTED_TEXT = "this test will validate that our qr code is generated as expected.";

    @Test
    void generate() {
        QRUtils qrUtils = QRUtils.get();
        BufferedImage qr = qrUtils.generate(EXPECTED_TEXT, 250);
        assertDoesNotThrow(() -> qrUtils.verifyQRCode(qr, EXPECTED_TEXT));
    }


    @Test
    void read() {
        QRUtils qrUtils = QRUtils.get();
        String result = qrUtils.read(IOUtils.getImage("src/test/resources/externalQR.png"));
        assertEquals(EXPECTED_RESULT, result);
    }

    @Test
    void testRead() {
        QRUtils qrUtils = QRUtils.get();
        BufferedImage qr = IOUtils.getImage("src/test/resources/externalQR.png");
        assertDoesNotThrow(() -> qrUtils.verifyQRCode(qr, EXPECTED_RESULT));
        assertThrows(ReadQRException.class, () -> qrUtils.verifyQRCode(qr, "must throw an error"));
    }
}