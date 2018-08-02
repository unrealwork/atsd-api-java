package com.axibase.tsd.client;

import com.axibase.tsd.util.AtsdUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.modules.junit4.PowerMockRunnerDelegate;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(PowerMockRunner.class)
@PowerMockRunnerDelegate(Parameterized.class)
@PrepareForTest( {AtsdUtil.class})
public class HttpUtilsTest {

    @Parameter
    public String clientName;
    @Parameter(1)
    public String version;
    @Parameter(2)
    public String expectedUserAgent;

    @Parameters(name = "Compiling of UserAgent header for version {0} and client name {1} should return {2}")
    public static Collection<Object[]> params() {
        return Arrays.asList(
                new Object[][] {
                        {"axibase-collector/19768", "1.0.6", "axibase-collector/19768 atsd-api-java/1.0.6"},
                        {null, "1.0.6", "atsd-api-java/1.0.6"},
                        {"axibase-collector/19768", null, "axibase-collector/19768 atsd-api-java"},
                        {null, null, "atsd-api-java"}
                }
        );
    }

    @Before
    public void init() {
        PowerMockito.mockStatic(AtsdUtil.class);
        PowerMockito.when(AtsdUtil.getVersion()).thenReturn(version);
    }

    @Test
    public void compileUserAgent() {

        final String assertMessage = String.format("Incorrect compiled user-agent name for version: %s and client name: %s",
                version, clientName);
        assertEquals(assertMessage, expectedUserAgent, HttpUtils.compileUserAgent(clientName));
    }
}