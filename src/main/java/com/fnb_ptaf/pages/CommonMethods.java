package com.fnb_ptaf.pages;

import com.fnb_ptaf.HandleAction;
import com.fnb_ptaf.WaitAction;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fnb_ptaf.GetTargetLocator;


public class CommonMethods implements HandleAction, WaitAction {
    private final Page page;
    private static final Logger logger = LoggerFactory.getLogger(CommonMethods.class);

    /**
     * Constructor to initialize the CommonMethods with a Page instance.
     *
     * @param page The Playwright Page instance
     */
    public CommonMethods(Page page) {
        this.page = page;
    }

    /**
     * Retrieves the title of the current page.
     *
     * @return The page title as a string
     */
    public String getTitle() {
        try {
            String title = page.title();
            if (title.isEmpty()) {
                logger.warn("The title of the page is empty.");
            } else {
                logger.info("Title of the page: {}", title);
            }
            return title;
        } catch (Exception e) {
            logger.error("Failed to retrieve page title", e);
            throw e;
        }
    }

    /**
     * Public method to perform a click action on an element within a specific context.
     * This method is designed for external use, allowing other parts of the code to
     * interact with elements by simulating a click.
     *
     * @param context The context in which the element is located (e.g., frame, window).
     * @param element The name of the element as defined in the YAML configuration.
     * @param locator The key to locate the element's selector.
     */
    public void clickOnLocator(String context, Page page, String element, String locator) {
        performActionOnContext("click", page, element, locator, null);
    }

    /**
     * Public method to perform a fill action on an element within a specific context.
     * This method is designed for external use, allowing other parts of the code to
     * interact with input fields by filling them with the specified value.
     *
     * @param context The context in which the element is located (e.g., frame, window).
     * @param element The name of the element as defined in the YAML configuration.
     * @param locator The key to locate the element's selector.
     * @param value   The value to fill into the input field.
     */
    public void fillOnLocator(String context, Page page, String element, String locator, String value) {
        performActionOnContext("fill", page, element, locator, value);
    }

    /**
     * Public method to perform a select option action on an element within a specific context.
     * This method is designed for external use, allowing other parts of the code to
     * interact with dropdowns or selection elements by selecting the specified option.
     *
     * @param context The context in which the element is located (e.g., frame, window).
     * @param element The name of the element as defined in the YAML configuration.
     * @param locator The key to locate the element's selector.
     * @param value   The option to select from the dropdown or selection element.
     */
    public void selectOptionOnLocator(String context, Page page, String element, String locator, String value) {
        performActionOnContext("select", page, element, locator, value);
    }

    /**
     * Performs a check action on a specified element.
     * This method is used to check a checkbox or radio button element.
     *
     * @param element The name of the element as defined in the configuration.
     * @param locator The locator key to locate the element on the page.
     */
    public void check(String element, String locator) {
        performAction("check", page, element, locator, null);
    }

    /**
     * Performs an uncheck action on a specified element.
     * This method is used to uncheck a checkbox or radio button element.
     *
     * @param element The name of the element as defined in the configuration.
     * @param locator The locator key to locate the element on the page.
     */
    public void uncheck(String element, String locator) {
        performAction("uncheck", page, element, locator, null);
    }

    /**
     * Performs a hover action over a specified element.
     * This method moves the mouse cursor over the element to trigger hover effects.
     *
     * @param element The name of the element as defined in the configuration.
     * @param locator The locator key to locate the element on the page.
     */
    public void hover(String element, String locator) {
        performAction("hover", page, element, locator, null);
    }

    /**
     * Performs a type action on a specified element.
     * This method types a text value into an input field or textarea.
     *
     * @param element The name of the element as defined in the configuration.
     * @param locator The locator key to locate the element on the page.
     */
    public void type(String element, String locator) {
        performAction("type", page, element, locator, null);
    }

    /**
     * Performs a press action on a specified element.
     * This method simulates a key press on the element, such as pressing a keyboard key.
     *
     * @param element The name of the element as defined in the configuration.
     * @param locator The locator key to locate the element on the page.
     */
    public void press(String element, String locator) {
        performAction("press", page, element, locator, null);
    }

    /**
     * Performs a dblclick action on a specified element.
     * This method simulates a dblclick action on the element, useful for triggering double-click events.
     *
     * @param element The name of the element as defined in the configuration.
     * @param locator The locator key to locate the element on the page.
     */
    public void dblclick(String element, String locator) {
        performAction("dblclick", page, element, locator, null);
    }

    /**
     * A wrapper method to perform actions within a specific context, with additional error handling.
     * This method simplifies the interaction with elements across different contexts (e.g., frames, windows).
     *
     * @param action  The action to perform (e.g., click, fill, select).
     * @param element The name of the element as defined in the YAML configuration.
     * @param locator The key to locate the element's selector.
     * @param value   The value to be used in the action, if applicable (e.g., text to fill).
     */
    private void performActionOnContext(String action, Page page, String element, String locator, String value) {
        try {
            performAction(action, page, element, locator, value);
        } catch (Exception e) {
            logger.error("Failed to perform '{}' action on element '{}'", action, element, e);
        }
    }

    private void performAction(String action, Page page, String element, String key, String value) {
        try {
            Locator targetLocator = GetTargetLocator.getTargetLocator(page, element, key);
            WaitAction.WAIT(targetLocator);
            HandleAction.ACTION(targetLocator, action, value);
        } catch (Exception e) {
            logger.error("Failed to perform '{}' action on element by Locator '{}'", action, element + key, e);
        }
    }
}
