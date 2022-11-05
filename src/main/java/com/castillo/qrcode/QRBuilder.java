package com.castillo.qrcode;

import com.castillo.qrcode.draw.BackgroundPainter;
import com.castillo.qrcode.draw.CodePainter;
import com.castillo.qrcode.draw.LogoPainter;
import com.castillo.qrcode.utils.QRUtils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.nio.charset.Charset;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.stream.Stream;

import static java.util.Objects.requireNonNull;

public class QRBuilder {
    private QRBuilder(){

    }
    public static QRWrapper build(Consumer<QRBuilder.Builder> builderConsumer){
        Builder builder = new Builder();
        builderConsumer.accept(builder);
        return new QRWrapper(builder.build());
    }

    public static class Builder {
        private String data;
        private Integer sizeInPixels;
        private Charset charSet;
        private BackgroundPainter backgroundPainter;
        private CodePainter codePainter;
        private LogoPainter logoPainter;

        private Builder(){
            super();
        }

        public QRBuilder.Builder withData(String data) {
            this.data = data;
            return this;
        }

        public QRBuilder.Builder withSizeInPixels(int sizeInPixels) {
            this.sizeInPixels = sizeInPixels;
            return this;
        }

        public QRBuilder.Builder withChartSet(Charset charset){
            this.charSet = charset;
            return this;
        }

        public QRBuilder.Builder withBackgroundColor(Color color){
            this.backgroundPainter = new BackgroundPainter(color);
            return this;
        }

        public QRBuilder.Builder withColor(Color color){
            this.codePainter = new CodePainter(color);
            return this;
        }

        public QRBuilder.Builder withLogo(BufferedImage bufferedImage){
            this.logoPainter = LogoPainter.of(bufferedImage);
            return this;
        }
        private void validateMandatory(){
            requireNonNull(this.data, "the qr code require some input data");
            requireNonNull(this.sizeInPixels, "you need to specify the size of the code in pixels");
        }

        private QRWrapper generateQr(){
            QRUtils qrUtils = this.charSet == null ? QRUtils.get() : new QRUtils(this.charSet);
            return new QRWrapper(qrUtils.generate(this.data, this.sizeInPixels));
        }
        private BufferedImage build(){
            this.validateMandatory();
            QRWrapper qrWrapper = generateQr();
            Stream.of(this.backgroundPainter, this.codePainter, this.logoPainter)
                    .filter(Objects::nonNull)
                    .forEach(qrWrapper::paint);
            return qrWrapper.toImage();
        }
    }


}
