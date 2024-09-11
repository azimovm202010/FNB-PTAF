package com.fnb_ptaf;

public interface GetActualLocator {

    /**
     * Utility method to extract the actual locator string from the locator value.
     */
    static String getActualLocator(String locatorValue) {
        int firstUnderscoreIndex = locatorValue.indexOf("_");
        if (firstUnderscoreIndex != -1) {
            return locatorValue.substring(firstUnderscoreIndex + 1);
        } else {
            return ""; // Handle this case as needed
        }
    }
}
