package com.fnb_ptaf.utils;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.WaitForSelectorState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class AssertionUtil {

    private final Page page;
    private static final Logger logger = LoggerFactory.getLogger(AssertionUtil.class);

    public AssertionUtil(Page page) {
        this.page = page;
    }

    /**
     * Asserts that the actual text of an element contains the expected text.
     *
     * @param element      The name of the element as defined in the YAML configuration.
     * @param key          The key to locate the element's selector.
     * @param expectedText The text expected to be found within the element.
     */
    public void assertContainsText(String element, String key, String expectedText) {
        String locatorValue = getElement(element, key);
        String[] eleArray = locatorValue.split("_");
        String locatorType = eleArray[0];
        String locator = eleArray[1];

        String actualText = getElementText(locatorType, locator);

        if (!actualText.contains(expectedText)) {
            throw new AssertionError("Text not found: Expected '" + expectedText + "' but found '" + actualText + "' in element with locator '" + locator + "'");
        }
    }

    /**
     * Asserts that an element is visible on the page.
     *
     * @param element The name of the element as defined in the YAML configuration.
     * @param key     The key to locate the element's selector.
     */
    public void assertElementVisible(String element, String key) {
        String locatorValue = getElement(element, key);
        String[] eleArray = locatorValue.split("_");
        String locatorType = eleArray[0];
        String locator = eleArray[1];

        Locator targetLocator = getLocatorByType(locatorType, locator);
        assertThat(targetLocator).isVisible();

        if (!targetLocator.isVisible()) {
            throw new AssertionError("Element is not visible: Locator '" + locator + "'");
        }
    }

    /**
     * Asserts that the value of an element matches the expected value.
     *
     * @param element      The name of the element as defined in the YAML configuration.
     * @param key          The key to locate the element's selector.
     * @param expectedValue The value expected to be found within the element.
     */
    public void assertHasValue(String element, String key, String expectedValue) {
        String locatorValue = getElement(element, key);
        String[] eleArray = locatorValue.split("_");
        String locatorType = eleArray[0];
        String locator = eleArray[1];

        if (!doesElementValueMatch(locatorType, locator, expectedValue)) {
            throw new AssertionError("Value not found: Expected '" + expectedValue + "' but found different value in element with locator '" + locator + "'");
        }
    }

    /**
     * Checks if the value of an element matches the expected value based on the locator type and value.
     *
     * @param locatorType   The type of locator (e.g., XPATH, CSS).
     * @param locator       The locator string used to identify the element.
     * @param expectedValue The value expected to be found within the element.
     * @return True if the element's value matches the expected value, false otherwise.
     */
    private boolean doesElementValueMatch(String locatorType, String locator, String expectedValue) {
        String actualValue;
        switch (locatorType) {
            case "XPATH":
            case "CSS":
            case "Tag":
                actualValue = page.locator(locator).inputValue();
                break;
            case "Text":
                actualValue = page.getByText(locator).inputValue();
                break;
            case "Placeholder":
                actualValue = page.getByPlaceholder(locator).inputValue();
                break;
            case "Label":
                actualValue = page.getByLabel(locator).inputValue();
                break;
            case "TestId":
                actualValue = page.getByTestId(locator).inputValue();
                break;
            case "Id":
                actualValue = page.locator("#" + locator).inputValue();
                break;
            case "Name":
                actualValue = page.locator("[name='" + locator + "']").inputValue();
                break;
            case "Class":
                actualValue = page.locator("." + locator).inputValue();
                break;
            case "Role":
                actualValue = page.getByRole(AriaRole.valueOf(locator)).inputValue();
                break;
            case "AltText":
                actualValue = page.getByAltText(locator).inputValue();
                break;
            case "Title":
                actualValue = page.getByTitle(locator).inputValue();
                break;
            default:
                throw new IllegalArgumentException("Unknown locator type: " + locatorType);
        }
        return actualValue.equals(expectedValue);
    }

    /**
     * Retrieves the element locator value based on the element name and key from YAML configuration.
     *
     * @param element The name of the element as defined in the YAML configuration.
     * @param key     The key to locate the element's selector.
     * @return The locator value as a string.
     */
    private String getElement(String element, String key) {
        try {
            return (String) YamlReader.get("elements." + element + "." + key);
        } catch (Exception e) {
            logger.error("Failed to retrieve selector for element '{}'", element + key, e);
            throw e;
        }
    }

    /**
     * Retrieves the text content of an element based on the locator type and value.
     *
     * @param locatorType The type of locator (e.g., XPATH, CSS).
     * @param locator     The locator string used to identify the element.
     * @return The text content of the element.
     */
    private String getElementText(String locatorType, String locator) {
        switch (locatorType) {
            case "XPATH":
            case "CSS":
            case "Tag":
                return page.locator(locator).innerText();
            case "Text":
                return page.getByText(locator).innerText();
            case "Placeholder":
                return page.getByPlaceholder(locator).innerText();
            case "Label":
                return page.getByLabel(locator).innerText();
            case "TestId":
                return page.getByTestId(locator).innerText();
            case "Id":
                return page.locator("#" + locator).innerText();
            case "Name":
                return page.locator("[name='" + locator + "']").innerText();
            case "Class":
                return page.locator("." + locator).innerText();
            case "Role":
                return page.getByRole(AriaRole.valueOf(locator)).innerText();
            case "AltText":
                return page.getByAltText(locator).innerText();
            case "Title":
                return page.getByTitle(locator).innerText();
            default:
                throw new IllegalArgumentException("Unknown locator type: " + locatorType);
        }
    }

    /**
     * Retrieves the appropriate Locator object based on the locator type.
     *
     * @param locatorType The type of locator (e.g., XPATH, CSS).
     * @param locator     The locator string used to identify the element.
     * @return The Locator object.
     */
    private Locator getLocatorByType(String locatorType, String locator) {
        switch (locatorType) {
            case "XPATH":
            case "CSS":
            case "Tag":
                return page.locator(locator);
            case "Text":
                return page.getByText(locator);
            case "Button":
                return page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName(locator));
            case "LinkText":
                return page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName(locator));
            case "Link":
                return page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName(locator).setExact(true));
            case "Heading":
                return page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName(locator));
            case "Heading1":
                return page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName(locator).setExact(true));
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
 }


