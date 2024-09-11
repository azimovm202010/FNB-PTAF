package com.fnb_ptaf;

import com.microsoft.playwright.Locator;

public interface HandleAction {
    /**
     * Executes the specified action on the provided Locator.
     * Supported actions include clicking, filling input fields, selecting options, etc.
     *
     * @param locator The Locator of the element to interact with.
     * @param action  The action to perform (e.g., click, fill, select).
     * @param value   The value to be used with the action, if applicable.
     * @throws IllegalArgumentException if the action is unknown.
     */
     static void ACTION(Locator locator, String action, String value) {
        switch (action) {
            case "click":
                locator.first().click();
                break;
            case "fill":
                locator.fill(value);
                break;
            case "select":
                locator.selectOption(value);
                break;
            case "check":
                locator.check();
                break;
            case "uncheck":
                locator.uncheck();
                break;
            case "hover":
                locator.hover();
                break;
            case "type":
                locator.type(value);
                break;
            case "press":
                locator.press(value);
                break;
            case "dblclick":
                locator.dblclick();
                break;
            default:
                throw new IllegalArgumentException("Unknown action: " + action);
        }
    }

}
