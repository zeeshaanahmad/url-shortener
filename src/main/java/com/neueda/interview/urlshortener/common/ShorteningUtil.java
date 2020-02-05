package com.neueda.interview.urlshortener.common;

public class ShorteningUtil {
    public static final String ALPHABET = "Mheo9PI2qNs5Zpf80TBn7lmRbtQ4YKXHvwAEWxuzdra316OJigGLSVUCyFjkDc";
    public static final int BASE = ALPHABET.length();

    public static String idToStr(Long num) {
        StringBuilder str = new StringBuilder();
        while (num > 0) {
            str.insert(0, ALPHABET.charAt((int) (num % BASE)));
            num = num / BASE;
        }
        return str.toString();
    }

    public static Long strToId(String str) {
        Long num = 0L;
        for (int i = 0; i < str.length(); i++) {
            num = num * BASE + ALPHABET.indexOf(str.charAt(i));
        }
        return num;
    }

    private ShorteningUtil() {}

}
