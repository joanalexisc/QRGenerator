package com.castillo.qrcode.utils;

import static java.util.Objects.nonNull;

public class ObjectUtils {
    public static <T> T or(T value, T defaultValue){
        return nonNull(value) ? value : defaultValue;
    }
    public static <T> T or(boolean condition, T trueValue, T falseValue){
        return condition ? trueValue : falseValue;
    }
}
