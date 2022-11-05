package com.castillo.qrcode.utils;

import com.castillo.qrcode.utils.ObjectUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ObjectUtilsTest {

    @Test
    void or() {
        String value = null;
        value = ObjectUtils.or(value, "new");
        assertEquals("new", value);
        value = ObjectUtils.or(value,"new++");
        assertNotEquals("new++", value);
    }

    @Test
    void orBoolean() {
        assertEquals(1, ObjectUtils.or(true,1,0));
        assertEquals(0, ObjectUtils.or(false,1,0));
    }


}