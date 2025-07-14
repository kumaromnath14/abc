package com.test.framework.prompts;

import java.util.HashMap;
import java.util.Map;

public class HomePagePrompt {

    // Navigation Prompts
    public static final String NAVIGATE_TO_HOME = "Navigate to AJIO homepage";
    public static final String VERIFY_HOME_PAGE = "Verify user is on AJIO homepage";
    public static final String VERIFY_MEN_TAB = "Verify MEN tab is displayed";
    public static final String VERIFY_WOMEN_TAB = "Verify WOMEN tab is displayed";

    // Action Prompts
    public static final String CLICK_MEN_TAB = "Click on MEN tab";
    public static final String CLICK_WOMEN_TAB = "Click on WOMEN tab";
    public static final String WAIT_FOR_PAGE_LOAD = "Wait for page to load completely";

    // Validation Prompts
    public static final String VALIDATE_MEN_SECTION = "Validate user is in MEN section";
    public static final String VALIDATE_WOMEN_SECTION = "Validate user is in WOMEN section";
    public static final String VALIDATE_URL = "Validate current URL contains expected section";

    // Error Messages
    public static final String ERROR_HOME_PAGE_NOT_LOADED = "AJIO homepage failed to load";
    public static final String ERROR_MEN_TAB_NOT_VISIBLE = "MEN tab is not visible on the page";
    public static final String ERROR_WOMEN_TAB_NOT_VISIBLE = "WOMEN tab is not visible on the page";
    public static final String ERROR_NAVIGATION_FAILED = "Failed to navigate to the specified section";

    // Success Messages
    public static final String SUCCESS_HOME_PAGE_LOADED = "Successfully loaded AJIO homepage";
    public static final String SUCCESS_MEN_SECTION = "Successfully navigated to MEN section";
    public static final String SUCCESS_WOMEN_SECTION = "Successfully navigated to WOMEN section";

    // Test Data
    private static final Map<String, String> expectedUrls = new HashMap<>();
    static {
        expectedUrls.put("MEN", "/shop/men");
        expectedUrls.put("WOMEN", "/shop/women");
    }

    // Timeouts
    public static final int PAGE_LOAD_TIMEOUT = 30;
    public static final int ELEMENT_WAIT_TIMEOUT = 10;
    public static final int NAVIGATION_WAIT_TIMEOUT = 5;

    // Helper Methods
    public static String getExpectedUrl(String section) {
        return expectedUrls.getOrDefault(section.toUpperCase(), "");
    }

    public static String getNavigationPrompt(String section) {
        return String.format("Navigate to %s section", section.toUpperCase());
    }

    public static String getValidationPrompt(String section) {
        return String.format("Validate user is in %s section", section.toUpperCase());
    }

    public static String getNavigationErrorMessage(String section) {
        return String.format("Failed to navigate to %s section", section.toUpperCase());
    }

    public static String getSuccessMessage(String section) {
        return String.format("Successfully navigated to %s section", section.toUpperCase());
    }

    // Test Step Templates
    public static class TestSteps {
        public static String navigateToHomePage() {
            return String.join("\n",
                    "1. Open browser",
                    "2. Navigate to AJIO homepage",
                    "3. Verify page is loaded completely",
                    "4. Verify MEN and WOMEN tabs are visible");
        }

        public static String navigateToSection(String section) {
            return String.join("\n",
                    String.format("1. Verify %s tab is visible", section),
                    String.format("2. Click on %s tab", section),
                    "3. Wait for page to load",
                    String.format("4. Verify URL contains '%s'", getExpectedUrl(section)),
                    String.format("5. Verify user is in %s section", section));
        }

        public static String switchSections(String fromSection, String toSection) {
            return String.join("\n",
                    String.format("1. Verify current section is %s", fromSection),
                    String.format("2. Click on %s tab", toSection),
                    "3. Wait for page load",
                    String.format("4. Verify URL contains '%s'", getExpectedUrl(toSection)),
                    String.format("5. Verify user is in %s section", toSection));
        }
    }

    // Assertion Messages
    public static class AssertionMessages {
        public static String elementVisible(String element) {
            return String.format("%s should be visible on the page", element);
        }

        public static String urlContains(String section) {
            return String.format("URL should contain '%s' after clicking %s tab",
                    getExpectedUrl(section), section);
        }

        public static String sectionLoaded(String section) {
            return String.format("%s section should be loaded correctly", section);
        }
    }
}
