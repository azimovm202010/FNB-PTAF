package com.fnb_ptaf;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public interface GetLocatorForType {

    /**
     * Determines and returns the appropriate Locator based on the specified locator type.
     * This method is designed for use in page contexts and supports various locator types,
     * such as XPATH, CSS, Role, and others.
     *
     * @param locatorType The type of locator (e.g., XPATH, CSS).
     * @param page        The Page object in which to locate the element.
     * @param locator     The locator string used to identify the element.
     * @return The appropriate Locator for the specified element.
     * @throws IllegalArgumentException if the locator type is unknown.
     */
    static Locator getLocatorForType(String locatorType, Page page, String locator) {
        switch (locatorType) {
            case "XPATH":
            case "CSS":
            case "Tag":
                return page.locator(locator);
            case "Button":
                return page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName(locator));
            case "LinkText":
                return page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName(locator));
            case "Link":
                return page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName(locator).setExact(true));
            case "Heading":
                return page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName(locator));
            case "Text":
                return page.getByText(locator);
            case "Role":
                return page.getByRole(AriaRole.valueOf(locator));
            case "AltText":
                return page.getByAltText(locator);
            case "Title":
                return page.getByTitle(locator);
            case "Placeholder":
                return page.getByPlaceholder(locator);
            case "Label":
                return page.getByLabel(locator);
            case "TestId":
                return page.getByTestId(locator);
            case "Id":
                return page.locator("#" + locator);
            case "Name":
                return page.locator("[name='" + locator + "']");
            case "Class":
                return page.locator("." + locator);
            default:
                throw new IllegalArgumentException("Unknown locator type: " + locatorType);
        }
    }
}
