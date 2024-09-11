package com.fnb_ptaf;

import com.fnb_ptaf.pages.CommonMethods;
import com.fnb_ptaf.utils.YamlReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public interface GetElement {
    Logger logger = LoggerFactory.getLogger(CommonMethods.class);
    /**
     * Retrieves the CSS selector for a given element and key from the YAML configuration.
     *
     * @param element The name of the element (e.g., button, input)
     * @param key     The specific key within the element (e.g., CSS selector, ID)
     * @return The CSS selector as a string
     */
    static String getElement(String element, String key) {
        try {
            return (String) YamlReader.get("elements." + element + "." + key);
        } catch (Exception e) {
            logger.error("Failed to retrieve selector for element '{}'", element + key, e);
            throw e;
        }
    }
}
