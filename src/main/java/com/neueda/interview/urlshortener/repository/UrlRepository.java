package com.neueda.interview.urlshortener.repository;

import com.neueda.interview.urlshortener.model.UrlEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UrlRepository extends JpaRepository<UrlEntity, Long> {

    @Query("SELECT u FROM url u WHERE u.fullUrl = ?1")
    List<UrlEntity> findUrlByFullUrl(String fullUrl);
}
