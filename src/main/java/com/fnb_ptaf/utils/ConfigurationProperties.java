package com.fnb_ptaf.utils;

public class ConfigurationProperties {

    /**
     * Retrieves the base URL from the YAML configuration.
     *
     * @param URL The key for the base URL in the YAML file
     * @return The base URL as a string
     */
    public static String getBaseUrl(String URL) {
        // Retrieve and return the base URL from the YAML file using the provided key
        return (String) YamlReader.get(URL);
    }

    /**
     * Retrieves the browser type from the YAML configuration.
     *
     * @return The browser type as a string
     */
    public static String getBrowser() {
        // Retrieve and return the browser type from the YAML file
        return (String) YamlReader.get("browser");
    }
}
