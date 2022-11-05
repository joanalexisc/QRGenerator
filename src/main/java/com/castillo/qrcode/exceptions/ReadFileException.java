package com.castillo.qrcode.exceptions;

public class ReadFileException extends QRException {
    public ReadFileException(String message) {
        super(message);
    }

    public ReadFileException(String message, Throwable cause) {
        super(message, cause);
    }
}
