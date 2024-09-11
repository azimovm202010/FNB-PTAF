package com.fnb_ptaf.utils;

import com.microsoft.playwright.Page;

/**
 * The KeyboardActionHandler class is designed to simulate keyboard actions
 * using Playwright's Page object. It provides methods to press various keys
 * on the keyboard.
 */
public class KeyboardActionHandler {
    private Page page;  // Playwright Page object used to interact with the browser

    /**
     * Constructor to initialize the Page object.
     * 
     * @param page The Playwright Page object.
     */
    public KeyboardActionHandler(Page page) {
        this.page = page;
    }

    /**
     * Simulates pressing a specified key on the keyboard.
     *
     * @param key The name of the key to press (e.g., "Backquote", "Minus").
     * @throws IllegalArgumentException If the provided key is not recognized.
     */
    private void pressKey(String key) {
        switch (key) {
            case "Backquote":
                page.keyboard().press("`");  // Press the backquote (`) key
                break;
            case "Minus":
                page.keyboard().press("-");  // Press the minus (-) key
                break;
            case "Equal":
                page.keyboard().press("=");  // Press the equal (=) key
                break;
            case "Backslash":
                page.keyboard().press("\\"); // Press the backslash (\) key
                break;
            case "Backspace":
                page.keyboard().press("Backspace");  // Press the backspace key
                break;
            case "Tab":
                page.keyboard().press("Tab");  // Press the tab key
                break;
            case "Delete":
                page.keyboard().press("Delete");  // Press the delete key
                break;
            case "Escape":
                page.keyboard().press("Escape");  // Press the escape key
                break;
            case "ArrowDown":
                page.keyboard().press("ArrowDown");  // Press the down arrow key
                break;
            case "End":
                page.keyboard().press("End");  // Press the end key
                break;
            case "Enter":
                page.keyboard().press("Enter");  // Press the enter key
                break;
            case "Home":
                page.keyboard().press("Home");  // Press the home key
                break;
            case "Insert":
                page.keyboard().press("Insert");  // Press the insert key
                break;
            case "PageDown":
                page.keyboard().press("PageDown");  // Press the page down key
                break;
            case "PageUp":
                page.keyboard().press("PageUp");  // Press the page up key
                break;
            case "ArrowRight":
                page.keyboard().press("ArrowRight");  // Press the right arrow key
                break;
            case "ArrowUp":
                page.keyboard().press("ArrowUp");  // Press the up arrow key
                break;
            default:
                throw new IllegalArgumentException("Unknown key: " + key);  // Handle unknown keys
        }
    }

    /**
     * Presses the End key.
     */
    public void pressEnd() {
        pressKey("End");
    }

    /**
     * Presses the Backquote key.
     */
    public void pressBackquote() {
        pressKey("Backquote");
    }

    /**
     * Presses the Minus key.
     */
    public void pressMinus() {
        pressKey("Minus");
    }

    /**
     * Presses the Equal key.
     */
    public void pressEqual() {
        pressKey("Equal");
    }

    /**
     * Presses the Backslash key.
     */
    public void pressBackslash() {
        pressKey("Backslash");
    }

    /**
     * Presses the Backspace key.
     */
    public void pressBackspace() {
        pressKey("Backspace");
    }

    /**
     * Presses the Tab key.
     */
    public void pressTab() {
        pressKey("Tab");
    }

    /**
     * Presses the Delete key.
     */
    public void pressDelete() {
        pressKey("Delete");
    }

    /**
     * Presses the Escape key.
     */
    public void pressEscape() {
        pressKey("Escape");
    }

    /**
     * Presses the ArrowDown key.
     */
    public void pressArrowDown() {
        pressKey("ArrowDown");
    }

    /**
     * Presses the Enter key.
     */
    public void pressEnter() {
        pressKey("Enter");
    }

    /**
     * Presses the Home key.
     */
    public void pressHome() {
        pressKey("Home");
    }

    /**
     * Presses the Insert key.
     */
    public void pressInsert() {
        pressKey("Insert");
    }

    /**
     * Presses the PageDown key.
     */
    public void pressPageDown() {
        pressKey("PageDown");
    }

    /**
     * Presses the PageUp key.
     */
    public void pressPageUp() {
        pressKey("PageUp");
    }

    /**
     * Presses the ArrowRight key.
     */
    public void pressArrowRight() {
        pressKey("ArrowRight");
    }

    /**
     * Presses the ArrowUp key.
     */
    public void pressArrowUp() {
        pressKey("ArrowUp");
    }
}
