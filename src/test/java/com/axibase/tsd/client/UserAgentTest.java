package com.axibase.tsd.client;

import com.axibase.tsd.TestUtil;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class UserAgentTest {
    private HttpClientManager client;

    @Before
    public void init() {
        client = TestUtil.buildHttpClientManager();
    }

    @Test
    public void testUserAgent() {
        final String actual = client.getClientConfiguration().getUserAgent();
        final String message = "User-agent value is different from default";
        Assert.assertEquals(message, "atsd-api-java", actual);
    }
}
