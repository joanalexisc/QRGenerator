package com.castillo.qrcode.exceptions;

public abstract class QRException extends RuntimeException {
    public QRException() {
    }

    public QRException(String message) {
        super(message);
    }

    public QRException(String message, Throwable cause) {
        super(message, cause);
    }

    public QRException(Throwable cause) {
        super(cause);
    }

    public QRException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
