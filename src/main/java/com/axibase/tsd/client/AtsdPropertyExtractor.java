package com.axibase.tsd.client;

import com.axibase.tsd.util.AtsdUtil;
import lombok.RequiredArgsConstructor;

import java.util.Properties;

/**
 * Extract atsd properties from file.
 */
@RequiredArgsConstructor
public class AtsdPropertyExtractor {
    private static final String AXIBASE_TSD_API_DOMAIN = "axibase.tsd.api.";
    private final Properties clientProperties;

    /**
     * Get property by name as long value
     *
     * @param name         name of property without the prefix.
     * @param defaultValue default value for case when the property is not set.
     * @return property's value.
     */
    public long getAsLong(final String name, final long defaultValue) {
        return AtsdUtil.getPropertyLongValue(fullName(name), clientProperties, defaultValue);
    }

    /**
     * Get property by name as int value
     *
     * @param name         name of property without the prefix.
     * @param defaultValue default value for case when the property is not set.
     * @return property's value.
     */
    public int getAsInt(final String name, final int defaultValue) {
        return AtsdUtil.getPropertyIntValue(fullName(name), clientProperties, defaultValue);
    }

    /**
     * Get property by name as boolean value
     *
     * @param name         name of property without the prefix.
     * @param defaultValue default value for case when the property is not set.
     * @return property's value.
     */
    public boolean getAsBoolean(final String name, final boolean defaultValue) {
        return AtsdUtil.getPropertyBoolValue(fullName(name), clientProperties, defaultValue);
    }

    private String fullName(final String name) {
        return String.format("%s%s", AXIBASE_TSD_API_DOMAIN, name);
    }

    /**
     * Get property by name as {@link String} value
     *
     * @param name         name of property without the prefix.
     * @param defaultValue default value for case when the property is not set.
     * @return property's value.
     */
    public String getAsString(final String name, final String defaultValue) {
        return AtsdUtil.getPropertyStringValue(fullName(name), clientProperties, defaultValue);
    }
}
