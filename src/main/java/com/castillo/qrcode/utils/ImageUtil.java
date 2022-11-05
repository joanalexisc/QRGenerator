package com.castillo.qrcode.utils;

import com.castillo.qrcode.draw.ColorReplacement;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.FilteredImageSource;

import static com.castillo.qrcode.utils.ObjectUtils.or;

public class ImageUtil {
    public static BufferedImage replaceColor(BufferedImage image, ColorReplacement replacement) {
        Image resImage = Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(image.getSource(), replacement));
        return toBufferedImage(resImage);
    }

    public static BufferedImage imageTransparency(BufferedImage image, float transparency){
        BufferedImage bufferedImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = (Graphics2D) bufferedImage.getGraphics();

        AlphaComposite comp = AlphaComposite.getInstance(
                AlphaComposite.SRC_OVER, transparency);
        g2d.setComposite(comp);
        g2d.drawImage(image, 0, 0, null);

        g2d.dispose();
        return bufferedImage;
    }
    public  static  BufferedImage resizeImage(BufferedImage image, int targetWidth, int targetHeight, int scale) {
        Image resultingImage = image.getScaledInstance(targetWidth/scale, targetHeight/scale, Image.SCALE_SMOOTH);
        BufferedImage outputImage = new BufferedImage(targetWidth/scale, targetHeight/scale, BufferedImage.TYPE_INT_ARGB);
        outputImage.getGraphics().drawImage(resultingImage, 0, 0, null);
        return outputImage;
    }

    public static BufferedImage drawOver(BufferedImage image, BufferedImage overImage) {
        return drawOver(image, overImage, true);
    }
    public static BufferedImage drawOver(BufferedImage image, BufferedImage overImage, boolean centered) {

        int deltaHeight = image.getHeight() - overImage.getHeight();
        int deltaWidth = image.getWidth() - overImage.getWidth();

        BufferedImage combined = new BufferedImage(image.getHeight(), image.getWidth(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = (Graphics2D) combined.getGraphics();

        g.drawImage(image, 0, 0, null);
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        int x = or(centered, Math.round(deltaWidth / 2), 0);
        int y = or(centered, Math.round(deltaHeight / 2), 0);
        g.drawImage(overImage, x , y, null);

        return combined;
    }

    public static BufferedImage toBufferedImage(Image img) {
        BufferedImage bImage = null;
        if (img instanceof BufferedImage) {
            bImage = (BufferedImage) img;
        } else {
            bImage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);
            Graphics2D bGr = bImage.createGraphics();
            bGr.drawImage(img, 0, 0, null);
            bGr.dispose();
        }
        return bImage;
    }
}
