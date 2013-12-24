package com.abctech.ripoti.webapp.util;

import org.apache.commons.codec.binary.Base64;

public final class Base64Util {

    /**
     * Encodes text with MIME base64
     * @param text
     * @return encoded text
     */
    public static String encode(String text) {
        if(text == null) {
            return null;
        }
        return new String(Base64.encodeBase64(text.getBytes()));
    }
}
