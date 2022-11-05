package com.castillo.qrcode.draw;

import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class ColorReplacementTest {

    @Test
    void of() {
        ColorReplacement colorReplacement = ColorReplacement.of(Color.RED, Color.BLUE);
        assertNotNull(colorReplacement);
    }

    @Test
    void filterRGB() {
        ColorReplacement colorReplacement = ColorReplacement.of(Color.RED, Color.BLUE);
        int result = colorReplacement.filterRGB(0, 0, Color.RED.getRGB());
        assertEquals(Color.BLUE.getRGB(), result);
        assertEquals(Color.GREEN.getRGB(), colorReplacement.filterRGB(0, 0, Color.GREEN.getRGB()));
    }
}