package com.castillo.qrcode.draw;

import com.castillo.qrcode.draw.interfaces.Painter;
import com.castillo.qrcode.utils.ImageUtil;

import java.awt.image.BufferedImage;

import static com.castillo.qrcode.utils.ObjectUtils.or;
import static java.util.Objects.requireNonNull;

public class LogoPainter implements Painter {
    public static final float DEFAULT_TRANSPARENCY = 1f;
    public static final int DEFAULT_IMAGE_SCALE = 4;

    private final BufferedImage image;
    private final Float transparency;
    private final int imageScale;
    public static LogoPainter of(BufferedImage image) {
        return new LogoPainter(image, null, null);
    }

    public LogoPainter(BufferedImage image, Integer imageScale, Float transparency) {
        requireNonNull(image, "Image is required");
        this.image = image;
        this.transparency = or(transparency, DEFAULT_TRANSPARENCY);
        this.imageScale = or(imageScale, DEFAULT_IMAGE_SCALE);
    }

    public BufferedImage draw(BufferedImage image) {
        BufferedImage logo = ImageUtil.resizeImage(this.image, image.getWidth(), image.getHeight(), imageScale);
        if (this.transparency != DEFAULT_TRANSPARENCY) {
            logo = ImageUtil.imageTransparency(logo, this.transparency);
        }
        return ImageUtil.drawOver(image, logo);
    }

}
