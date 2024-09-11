package com.fnb_ptaf;

public interface GetLocatorType {

    /**
     * Utility method to extract the locator type from the locator value.
     */
    static String getLocatorType(String locatorValue) {
        int firstUnderscoreIndex = locatorValue.indexOf("_");
        if (firstUnderscoreIndex != -1) {
            return locatorValue.substring(0, firstUnderscoreIndex);
        } else {
            return locatorValue;
        }
    }
}
