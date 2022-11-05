package com.castillo.qrcode;

import com.castillo.qrcode.draw.interfaces.Painter;
import com.castillo.qrcode.utils.FileFormat;
import com.castillo.qrcode.utils.IOUtils;
import com.castillo.qrcode.utils.QRUtils;

import java.awt.image.BufferedImage;

public class QRWrapper {
    private BufferedImage qrCode;

    public QRWrapper(BufferedImage qrCode) {
        this.qrCode = qrCode;
    }

    public void paint(Painter painter){
        this.qrCode = painter.draw(this.qrCode);
    }

    public BufferedImage toImage(){
        return this.qrCode;
    }

    public void save(String filePath){
        IOUtils.save(qrCode, filePath, FileFormat.PNG);
    }

    public String read(){
       return QRUtils.get().read(qrCode);
    }

}
