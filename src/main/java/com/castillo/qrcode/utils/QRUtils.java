package com.castillo.qrcode.utils;

import com.castillo.qrcode.exceptions.QRCodeException;
import com.castillo.qrcode.exceptions.ReadQRException;
import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeReader;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import java.awt.image.BufferedImage;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

public class QRUtils {

    private final Map<DecodeHintType, Object> decodeConfig = new HashMap<>();
    private final Map<EncodeHintType, Object> encodeConfig = new HashMap<>();

    public static QRUtils get(){
        return new QRUtils(Charset.defaultCharset());
    }

    public QRUtils(Charset charset){
        addEncodeConfig(charset);
        addDecodeConfig(charset);
    }

    private void addEncodeConfig(Charset charset){
        encodeConfig.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        encodeConfig.put(EncodeHintType.CHARACTER_SET, charset.name());
    }

    private void addDecodeConfig(Charset charset){
        decodeConfig.put(DecodeHintType.PURE_BARCODE, Boolean.TRUE);
        decodeConfig.put(DecodeHintType.TRY_HARDER, Boolean.TRUE);
        decodeConfig.put(DecodeHintType.CHARACTER_SET, charset.name());
    }


    public BufferedImage generate(String data, int sizeInPixels) {
        try {
            BitMatrix matrix = new QRCodeWriter().encode(data, BarcodeFormat.QR_CODE, sizeInPixels, sizeInPixels, encodeConfig);
            return MatrixToImageWriter.toBufferedImage(matrix);
        } catch (Exception e) {
            throw new QRCodeException("QRCode could not be generated", e);
        }
    }

    public String read(BufferedImage qrCode) {
        try {
            BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(qrCode)));
            Result result = new QRCodeReader().decode(binaryBitmap, decodeConfig);
            return result.getText();
        } catch (Exception e) {
            throw new ReadQRException("Couldn't read the QR data", e);
        }
    }

    public void verifyQRCode(BufferedImage qrCode, String data) {
        String actualData = read(qrCode);
        if (!data.equals(actualData)) {
            throw new ReadQRException(new StringBuilder(1000)
                    .append("The data contained in the qrCode is not equals as expected: ")
                    .append(data)
                    .append(" actual: ")
                    .append(actualData)
                    .toString());
        }
    }

}
