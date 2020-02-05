package com.neueda.interview.urlshortener.service;

import com.neueda.interview.urlshortener.repository.UrlRepository;
import com.neueda.interview.urlshortener.common.ShorteningUtil;
import com.neueda.interview.urlshortener.model.UrlEntity;
import com.neueda.interview.urlshortener.dto.FullUrl;
import com.neueda.interview.urlshortener.dto.ShortUrl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UrlService {

    @Autowired
    private UrlRepository urlRepository;

    private UrlEntity get(Long id) {
        UrlEntity urlEntity = urlRepository.findById(id).get();
        return urlEntity;
    }

    public FullUrl getFullUrl(String shortenString) {
        return new FullUrl(this.get(ShorteningUtil.strToId(shortenString)).getFullUrl());
    }

    private UrlEntity save(FullUrl fullUrl) {
        return urlRepository.save(new UrlEntity(fullUrl.getFullUrl()));
    }

    public ShortUrl getShortUrl(FullUrl fullUrl) {
        UrlEntity savedUrl = this.save(fullUrl);
        String shortUrlText = ShorteningUtil.idToStr(savedUrl.getId());
        return new ShortUrl(shortUrlText);
    }
}
