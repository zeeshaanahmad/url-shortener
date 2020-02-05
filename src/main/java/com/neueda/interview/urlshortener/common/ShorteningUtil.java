package com.neueda.interview.urlshortener.common;

/**
 * Provides encoding and decoding methods Base10 to Base62 and vice versa
 */
public class ShorteningUtil {
    /**
     * Randomized a-z A-Z 0-9 characters. Each character appears only once in the string
     */
    public static final String ALPHABET = "Mheo9PI2qNs5Zpf80TBn7lmRbtQ4YKXHvwAEWxuzdra316OJigGLSVUCyFjkDc";
    public static final int BASE = ALPHABET.length();

    /**
     * @param num Base10 number of type Long
     * @return base62 encoded string representation of input Long
     */
    public static String idToStr(Long num) {
        StringBuilder str = new StringBuilder();
        while (num > 0) {
            str.insert(0, ALPHABET.charAt((int) (num % BASE)));
            num = num / BASE;
        }
        return str.toString();
    }

    /**
     * @param str Base62 encoded string
     * @return decoded Base10 number of type Long
     */
    public static Long strToId(String str) {
        Long num = 0L;
        for (int i = 0; i < str.length(); i++) {
            num = num * BASE + ALPHABET.indexOf(str.charAt(i));
        }
        return num;
    }

    private ShorteningUtil() {}

}
