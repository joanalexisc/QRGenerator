package com.castillo.qrcode.exceptions;

public class ReadQRException extends QRException {
  public ReadQRException() {
    super();
  }

  public ReadQRException(String msg, Throwable cause){
    super(msg, cause);
  }

  public ReadQRException(String msg) {
    super(msg);
  }
}
