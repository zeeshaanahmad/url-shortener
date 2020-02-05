package com.neueda.interview.urlshortener.common;

import java.net.MalformedURLException;
import java.net.URL;

public class UrlUtil {
    private UrlUtil() {
    }

    public static String getBaseUrl(String url) throws MalformedURLException {
        URL reqUrl = new URL(url);
        String protocol = reqUrl.getProtocol();
        String host = reqUrl.getHost();
        int port = reqUrl.getPort();

        if (port == -1) {
            return String.format("%s://%s/", protocol, host);
        } else {
            return String.format("%s://%s:%d/", protocol, host, port);
        }

    }
}
