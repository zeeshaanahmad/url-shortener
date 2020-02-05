package com.neueda.interview.urlshortener.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.neueda.interview.urlshortener.dto.FullUrl;
import com.neueda.interview.urlshortener.service.UrlService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.startsWith;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class UrlControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private UrlService urlService;


    @Test
    public void givenFullUrlReturnStatusOk() throws Exception {
        FullUrl fullUrl = new FullUrl("https://example.com/foo");

        mvc.perform(post("/shorten")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(fullUrl)))
                .andExpect(status().isOk());
    }

    @Test
    public void givenFullUrlReturnJsonWithShortUrlProp() throws Exception {
        FullUrl fullUrlObj = new FullUrl("https://example.com/foo");

        mvc.perform(post("/shorten")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(fullUrlObj)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.shortUrl").exists());
    }

    @Test
    public void givenFullUrlReturnJsonWithShortUrlValueHasHttp() throws Exception {
        FullUrl fullUrl = new FullUrl("https://example.com/foo");
        mvc.perform(post("/shorten")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(fullUrl)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.shortUrl", startsWith("http")));
    }

    public static String asJsonString(final Object obj) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(obj);
    }

}