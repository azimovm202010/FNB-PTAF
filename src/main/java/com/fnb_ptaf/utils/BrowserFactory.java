package com.fnb_ptaf.utils;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Playwright;

public class BrowserFactory {

    public enum BrowserTypeEnum {
        CHROME,
        FIREFOX,
        WEBKIT
    }

    public static Browser createBrowser(BrowserTypeEnum browserTypeEnum) {
        Playwright playwright = Playwright.create();
        BrowserType browserType;
        switch (browserTypeEnum) {
            case CHROME:
                browserType = playwright.chromium();
                break;
            case FIREFOX:
                browserType = playwright.firefox();
                break;
            case WEBKIT:
                browserType = playwright.webkit();
                break;
            default:
                throw new IllegalArgumentException("Unsupported browser type");
        }

        // Configure the browser to run in headless mode
        Browser browser = browserType.launch(new BrowserType.LaunchOptions().setHeadless(false));
        return browser;
    }
}
