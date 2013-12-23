package com.abctech.ripoti.webapp.util;

import org.apache.commons.codec.binary.Base64;

public final class Base64Util {

    public static String encode(String text) {
        if(text == null) {
            return null;
        }
        return new String(Base64.encodeBase64(text.getBytes()));
    }
}
