package com.castillo.qrcode.draw;

import com.castillo.qrcode.draw.interfaces.Painter;
import com.castillo.qrcode.utils.ImageUtil;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class ColorPainter implements Painter {
    private ColorReplacement replacement;
    protected ColorPainter(Color source, Color target){
        this.replacement = ColorReplacement.of(source, target);
    }

    @Override
    public BufferedImage draw(BufferedImage image) {
        return ImageUtil.replaceColor(image, replacement);
    }
}
