package com.neueda.interview.urlshortener.common;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.net.MalformedURLException;

@RunWith(MockitoJUnitRunner.class)
public class UrlUtilTest {

    @Test(expected = MalformedURLException.class)
    public void shouldThrowExceptionWhenMalformedUrlSuppliedWithoutProtocol() throws MalformedURLException {
        UrlUtil.getBaseUrl("malformed url dummy text");
    }

    @Test(expected = MalformedURLException.class)
    public void shouldThrowExceptionWhenMalformedUrlSuppliedWithIllegalChars() throws MalformedURLException {
        UrlUtil.getBaseUrl("malformed://example.com/foo");
    }

    @Test
    public void shouldReturnBaseUrlWhenValidUrlSuppliedWithoutPort() throws MalformedURLException {
        Assert.assertEquals(UrlUtil.getBaseUrl("http://example.com/foo"), "http://example.com/");
    }

    @Test
    public void shouldReturnBaseUrlWhenValidUrlSuppliedWithPort() throws MalformedURLException {
        Assert.assertEquals(UrlUtil.getBaseUrl("http://example.com:8080/foo"), "http://example.com:8080/");
    }
}