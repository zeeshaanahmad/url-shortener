package com.neueda.interview.urlshortener.controller;

import com.neueda.interview.urlshortener.common.UrlUtil;
import com.neueda.interview.urlshortener.dto.ShortUrl;
import com.neueda.interview.urlshortener.error.InvalidUrlError;
import com.neueda.interview.urlshortener.dto.FullUrl;
import com.neueda.interview.urlshortener.service.UrlService;
import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.NoSuchElementException;

@RestController
public class UrlController {

    @Autowired
    UrlService urlService;

    @PostMapping("/shorten")
    public ResponseEntity<Object> saveUrl(@RequestBody FullUrl fullUrl, HttpServletRequest request) {
        UrlValidator validator = new UrlValidator(
                new String[]{"http", "https"}
        );
        String url = fullUrl.getFullUrl();
        if (!validator.isValid(url)) {
            InvalidUrlError error = new InvalidUrlError("url", fullUrl.getFullUrl(), "Invalid URL");
            return ResponseEntity.badRequest().body(error);
        }
        String baseUrl = null;

        try {
            baseUrl = UrlUtil.getBaseUrl(request.getRequestURL().toString());
        } catch (MalformedURLException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Request url is invalid", e);
        }

        ShortUrl shortUrl = urlService.getShortUrl(fullUrl);
        shortUrl.setShortUrl(baseUrl + shortUrl.getShortUrl());

        return new ResponseEntity<>(shortUrl, HttpStatus.OK);
    }

    @GetMapping("/{shortenString}")
    public void redirectToFullUrl(HttpServletResponse response, @PathVariable String shortenString) {
        try {
            FullUrl fullUrl = urlService.getFullUrl(shortenString);
            response.sendRedirect(fullUrl.getFullUrl());
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Url not found", e);
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Could not redirect to the full url", e);
        }
    }

}
