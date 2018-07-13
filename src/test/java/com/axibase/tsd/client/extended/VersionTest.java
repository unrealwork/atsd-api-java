package com.axibase.tsd.client.extended;

import com.axibase.tsd.TestUtil;
import com.axibase.tsd.client.ExtendedService;
import com.axibase.tsd.client.ServiceFactory;
import com.axibase.tsd.model.version.AtsdVersion;
import org.junit.Assert;
import org.junit.Test;

public class VersionTest {
    private ExtendedService extendedService = ServiceFactory
            .with(TestUtil.buildHttpClientManager())
            .extended();

    @Test
    public void testVersion() {
        final AtsdVersion version = extendedService.version();
        Assert.assertNotNull(version);
        Assert.assertNotNull(version.getBuildInfo());
        Assert.assertNotNull(version.getLicense());
        Assert.assertNotNull(version.getDate());
        Assert.assertNotNull(version.getSettings());
    }
}
