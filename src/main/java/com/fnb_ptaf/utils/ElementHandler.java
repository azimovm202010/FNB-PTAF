package com.fnb_ptaf.utils;

import com.fnb_ptaf.pages.CommonMethods;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.WaitForSelectorState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class ElementHandler {
    private Page page;
    private static final Logger logger = LoggerFactory.getLogger(CommonMethods.class);

    /**
     * Retrieves the CSS selector for a given element and key from the YAML configuration.
     *
     * @param element The name of the element (e.g., button, input)
     * @param key     The specific key within the element (e.g., CSS selector, ID)
     * @return The CSS selector as a string
     */
    public String getElement(String element, String key) {
        try {
            return (String) YamlReader.get("elements." + element + "." + key);
        } catch (Exception e) {
            logger.error("Failed to retrieve selector for element '{}'", element + key, e);
            throw e;
        }
    }
    /**
     * Retrieves an ElementHandle for the given element and key within a specific context.
     *
     * @param context The context in which the element is located (e.g., frame, window).
     * @param element The name of the element as defined in the YAML configuration.
     * @param locator The key to locate the element's selector.
     * @return The ElementHandle for the specified element.
     */
    public com.microsoft.playwright.ElementHandle getElementHandle(String context, String element, String locator) {
        try {
            String locatorValue = getElement(element, locator);

            String locatorType = getLocatorType(locatorValue);
            String actualLocator = getActualLocator(locatorValue);

            Locator targetLocator;

            switch (context) {
                case "page":
                    targetLocator = getLocatorForType(locatorType, page, actualLocator);
                    break;
                default:
                    throw new IllegalArgumentException("Unknown context: " + context);
            }

            waitForElementToBeDisplayed(targetLocator);
            return targetLocator.first().elementHandle();
        } catch (Exception e) {
            logger.error("Failed to retrieve ElementHandle for element '{}'", element + locator, e);
            throw e;
        }
    }

    public List<com.microsoft.playwright.ElementHandle> getElementHandleList(String context, String element, String locator) {
        List<com.microsoft.playwright.ElementHandle> elementHandles = new ArrayList<>();
        try {
            String locatorValue = getElement(element, locator);
            String locatorType = getLocatorType(locatorValue);
            String actualLocator = getActualLocator(locatorValue);

            Locator targetLocator;
            switch (context) {
                case "page":
                    targetLocator = getLocatorForType(locatorType, page, actualLocator);
                    break;
                default:
                    throw new IllegalArgumentException("Unknown context: " + context);
            }

            waitForElementToBeDisplayed(targetLocator);

            // Get all matching elements
            List<com.microsoft.playwright.ElementHandle> handles = targetLocator.elementHandles();
            if (handles != null) {
                elementHandles.addAll(handles);
            }
        } catch (Exception e) {
            logger.error("Failed to retrieve ElementHandles for element '{}'", element + locator, e);
            throw e;
        }
        return elementHandles;
    }

    /**
     * Checks if the element is enabled.
     *
     * @param context The context in which the element is located (e.g., frame, window).
     * @param element The name of the element as defined in the YAML configuration.
     * @param locator The key to locate the element's selector.
     * @return True if the element is enabled, false otherwise.
     */
    public boolean isEnabled(String context, String element, String locator) {
        try {
            com.microsoft.playwright.ElementHandle elementHandle = getElementHandle(context, element, locator);
            boolean isEnabled = elementHandle.isEnabled();
            logger.info("Element '{}' is enabled: {}", element, isEnabled);
            return isEnabled;
        } catch (Exception e) {
            logger.error("Failed to check if element '{}' is enabled", element, e);
            return false;
        }
    }

    /**
     * Checks if the element is disabled.
     *
     * @param context The context in which the element is located (e.g., frame, window).
     * @param element The name of the element as defined in the YAML configuration.
     * @param locator The key to locate the element's selector.
     * @return True if the element is disabled, false otherwise.
     */
    public boolean isDisabled(String context, String element, String locator) {
        try {
            com.microsoft.playwright.ElementHandle elementHandle = getElementHandle(context, element, locator);
            boolean isDisabled = !elementHandle.isEnabled();
            logger.info("Element '{}' is disabled: {}", element, isDisabled);
            return isDisabled;
        } catch (Exception e) {
            logger.error("Failed to check if element '{}' is disabled", element, e);
            return false;
        }
    }

    /**
     * Checks if the element is checked (e.g., checkbox or radio button).
     *
     * @param context The context in which the element is located (e.g., frame, window).
     * @param element The name of the element as defined in the YAML configuration.
     * @param locator The key to locate the element's selector.
     * @return True if the element is checked, false otherwise.
     */
    public boolean isChecked(String context, String element, String locator) {
        try {
            com.microsoft.playwright.ElementHandle elementHandle = getElementHandle(context, element, locator);
            boolean isChecked = elementHandle.isChecked();
            logger.info("Element '{}' is checked: {}", element, isChecked);
            return isChecked;
        } catch (Exception e) {
            logger.error("Failed to check if element '{}' is checked", element, e);
            return false;
        }
    }

    /**
     * Checks if the element is visible.
     *
     * @param context The context in which the element is located (e.g., frame, window).
     * @param element The name of the element as defined in the YAML configuration.
     * @param locator The key to locate the element's selector.
     * @return True if the element is visible, false otherwise.
     */
    public boolean isVisible(String context, String element, String locator) {
        try {
            com.microsoft.playwright.ElementHandle elementHandle = getElementHandle(context, element, locator);
            boolean isVisible = elementHandle.isVisible();
            logger.info("Element '{}' is visible: {}", element, isVisible);
            return isVisible;
        } catch (Exception e) {
            logger.error("Failed to check if element '{}' is visible", element, e);
            return false;
        }
    }

    /**
     * Checks if the element is hidden.
     *
     * @param context The context in which the element is located (e.g., frame, window).
     * @param element The name of the element as defined in the YAML configuration.
     * @param locator The key to locate the element's selector.
     * @return True if the element is hidden, false otherwise.
     */
    public boolean isHidden(String context, String element, String locator) {
        try {
            com.microsoft.playwright.ElementHandle elementHandle = getElementHandle(context, element, locator);
            boolean isHidden = elementHandle.isHidden();
            logger.info("Element '{}' is hidden: {}", element, isHidden);
            return isHidden;
        } catch (Exception e) {
            logger.error("Failed to check if element '{}' is hidden", element, e);
            return false;
        }
    }

    /**
     * Utility method to extract the locator type from the locator value.
     */
    private String getLocatorType(String locatorValue) {
        int firstUnderscoreIndex = locatorValue.indexOf("_");
        if (firstUnderscoreIndex != -1) {
            return locatorValue.substring(0, firstUnderscoreIndex);
        } else {
            return locatorValue;
        }
    }

    /**
     * Utility method to extract the actual locator string from the locator value.
     */
    private String getActualLocator(String locatorValue) {
        int firstUnderscoreIndex = locatorValue.indexOf("_");
        if (firstUnderscoreIndex != -1) {
            return locatorValue.substring(firstUnderscoreIndex + 1);
        } else {
            return ""; // Handle this case as needed
        }
    }

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
    private Locator getLocatorForType(String locatorType, Page page, String locator) {
        switch (locatorType) {
            case "XPATH":
            case "CSS":
            case "Tag":
                return page.locator(locator);
            case "Button":
                return page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName(locator));
            case "LinkText":
                return page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName(locator));
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

    /**
     * Waits until the specified element is visible on the page or within the frame.
     * This method is crucial to ensure that elements are interactable before actions are performed.
     * The timeout for this operation is set to 2 minutes (120,000 milliseconds).
     *
     * @param locator The Locator for the element to wait for.
     */
    private void waitForElementToBeDisplayed(Locator locator) {
        try {
            locator.waitFor(new Locator.WaitForOptions()
                    .setState(WaitForSelectorState.VISIBLE)
                    .setTimeout(120000)); // 2-minute timeout
        } catch (Exception e) {
            logger.error("Failed to wait for the element to be displayed", e);
        }
    }

    /**
     * Executes the specified action on the provided Locator.
     * Supported actions include clicking, filling input fields, selecting options, etc.
     *
     * @param locator The Locator of the element to interact with.
     * @param action  The action to perform (e.g., click, fill, select).
     * @param value   The value to be used with the action, if applicable.
     * @throws IllegalArgumentException if the action is unknown.
     */
    private void handleAction(Locator locator, String action, String value) {
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
