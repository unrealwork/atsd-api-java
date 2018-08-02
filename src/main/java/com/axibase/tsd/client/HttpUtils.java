package com.axibase.tsd.client;

import com.axibase.tsd.util.AtsdUtil;
import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.StringUtils;

@UtilityClass
class HttpUtils {
    static String compileUserAgent(final String clientName) {
        final String version = AtsdUtil.getVersion();

        final StringBuilder stringBuilder = new StringBuilder();
        if (StringUtils.isNotEmpty(clientName)) {
            stringBuilder.append(clientName).append(' ');
        }
        stringBuilder.append("atsd-api-java");
        if (StringUtils.isNotEmpty(version)) {
            stringBuilder.append('/').append(version);
        }
        return stringBuilder.toString();
    }

}
