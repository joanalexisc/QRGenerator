package com.castillo.qrcode.draw;

import java.awt.*;
import java.awt.image.RGBImageFilter;

import static com.castillo.qrcode.utils.ObjectUtils.or;

public class ColorReplacement extends RGBImageFilter {
    private final int fromColor;
    private final int toColor;

    public static ColorReplacement of(Color fromColor, Color toColor){
        return new ColorReplacement(fromColor, toColor);
    }

    public ColorReplacement(Color fromColor, Color toColor) {
        this.fromColor = fromColor.getRGB();
        this.toColor = toColor.getRGB();
    }


    @Override
    public int filterRGB(int x, int y, int rgb) {
        return or(rgb == fromColor,  toColor , rgb);
    }
}
