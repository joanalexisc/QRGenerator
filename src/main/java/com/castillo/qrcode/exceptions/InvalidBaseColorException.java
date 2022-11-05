package com.castillo.qrcode.exceptions;

public class InvalidBaseColorException extends QRException {
    public InvalidBaseColorException() {
        super("Invalid base color, Only white or black are allowed");
    }
}
