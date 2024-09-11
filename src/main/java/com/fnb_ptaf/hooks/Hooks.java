package com.fnb_ptaf.hooks;

import com.fnb_ptaf.utils.BrowserFactory;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Page;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Hooks {
    // Static variables for browser and page instances
    private static Browser browser;
    private static Page page;

    // Logger for logging information
    private static final Logger logger = LoggerFactory.getLogger(Hooks.class);

    /**
     * Method annotated with @Before to indicate it runs before each scenario.
     * Sets up the browser and page instances.
     */
    @Before
    public void setUp() {
        // Default to using Chrome browser, can be changed to other supported browsers
        BrowserFactory.BrowserTypeEnum browserTypeEnum = BrowserFactory.BrowserTypeEnum.CHROME;

        // Create a new browser instance with headless mode
        browser = BrowserFactory.createBrowser(browserTypeEnum);

        // Create a new page in the browser
        page = browser.newPage();

        // Log that the browser setup is complete
        logger.info("Browser setup completed: {}", browserTypeEnum);
    }

    /**
     * Method annotated with @After to indicate it runs after each scenario.
     * Cleans up by closing the page and browser instances, and takes a screenshot if the scenario fails.
     *
     * @param scenario The current scenario being executed
     */
    @After
    public void tearDown(Scenario scenario) {
        // Check if the scenario has failed
        if (scenario.isFailed()) {
            // Take a screenshot of the full page
            byte[] screenshot = page.screenshot(new Page.ScreenshotOptions().setFullPage(true));

            // Attach the screenshot to the scenario
            scenario.attach(screenshot, "image/png", scenario.getName());

            // Log that the scenario failed and a screenshot was taken
            logger.error("Scenario failed, screenshot taken: {}", scenario.getName());
        }

        // Close the page
        page.close();

        // Close the browser
        browser.close();
        // Log that the browser has been closed
        logger.info("Browser closed");
    }

    /**
     * Static method to get the current page instance.
     *
     * @return The current page instance
     */
    public static Page getPage() {
        return page;
    }
}