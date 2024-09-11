package com.fnb_ptaf;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import static com.fnb_ptaf.GetActualLocator.getActualLocator;
import static com.fnb_ptaf.GetElement.getElement;
import static com.fnb_ptaf.GetLocatorType.getLocatorType;

public interface GetTargetLocator {
     static Locator getTargetLocator(Page page, String element, String key) {
        String locatorValue = getElement(element, key);
        String locatorType = getLocatorType(locatorValue);
        String actualLocator = getActualLocator(locatorValue);

         try {
                 return GetLocatorForType.getLocatorForType(locatorType, page, actualLocator);
         } catch (IllegalArgumentException e) {
             throw new RuntimeException("Unknown context: " + page);
         }
     }
}


