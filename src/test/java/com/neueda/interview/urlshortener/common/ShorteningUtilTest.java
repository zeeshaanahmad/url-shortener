package com.neueda.interview.urlshortener.common;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ShorteningUtilTest {
    @Test
    public void shouldConvertMaxLongToShortString() {
        String maxIdShortString = ShorteningUtil.idToStr(Long.MAX_VALUE);
        Assert.assertNotNull(maxIdShortString);
        Assert.assertNotEquals(maxIdShortString, "");
    }

    @Test
    public void shouldThrowExceptionWhenShortStrLongerThanTenChars() {
        long id = ShorteningUtil.strToId("sclqgMAPqi2Z");
    }

}