package com.sofort.lib.core.internal.utils;

/**
 * "Key -&gt; value" pair container.
 */
public class Attribute {

    /**
     * The key.
     */
    private final String key;

    /**
     * The value.
     */
    private final String value;


    /**
     * Defines the key -&gt; value pair.
     *
     * @param key   key.
     * @param value value.
     */
    public Attribute(String key, String value) {
        this.key = key;
        this.value = value;
    }


    /**
     * Gets the key.
     *
     * @return defined key.
     */
    public String getKey() {
        return key;
    }


    /**
     * Gets the value.
     *
     * @return defined value.
     */
    public String getValue() {
        return value;
    }

}
