package com.fnb_ptaf;

import com.fnb_ptaf.pages.CommonMethods;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.options.WaitForSelectorState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public interface WaitAction {
    static final Logger logger = LoggerFactory.getLogger(CommonMethods.class);

    /**
     * Waits until the specified element is visible on the page or within the frame.
     * This method is crucial to ensure that elements are interactable before actions are performed.
     * The timeout for this operation is set to 2 minutes (120,000 milliseconds).
     *
     * @param locator The Locator for the element to wait for.
     */
    static void WAIT(Locator locator) {
        try {
            locator.waitFor(new Locator.WaitForOptions()
                    .setState(WaitForSelectorState.VISIBLE)
                    .setTimeout(120000)); // 2-minute timeout
        } catch (Exception e) {
            logger.error("Failed to wait for the element to be displayed", e);
        }
    }
}
