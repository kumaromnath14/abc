package com.test.framework;

import com.test.framework.context.ReportContext;
import com.test.framework.context.TestContext;
import com.test.framework.pages.VSCodePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class VSCodeTest extends BaseTest {

    @Test(description = "Navigate to VS Code Setup Documentation and Overview")
    public void testNavigateToVSCodeSetupAndOverview() {
        VSCodePage vscodePage = new VSCodePage(driver);
        TestContext context = TestContext.getInstance();

        try {
            // Initialize test context
            context.setTestName("VS Code Setup and Overview Navigation Test");
            context.setTestDescription("Navigate to VS Code setup documentation and Overview section");
            context.setTestStatus(TestContext.TestStatus.IN_PROGRESS);
            ReportContext.getInstance().clearSteps();

            // Step 1: Navigate to VS Code website
            ReportContext.addStep("Navigating to VS Code website");
            vscodePage.navigateToVSCodeSite();
            Assert.assertTrue(driver.getTitle().toLowerCase().contains("visual studio code"),
                    "Failed to load VS Code homepage");
            ReportContext.addStep("Successfully navigated to VS Code website");

            // Step 2: Click on Docs tab
            ReportContext.addStep("Clicking on Documentation tab");
            vscodePage.clickDocsTab();
            Assert.assertTrue(vscodePage.isDocsPageLoaded(),
                    "Documentation page failed to load");
            ReportContext.addStep("Successfully navigated to Documentation page");

            // Step 3: Click on Setup link
            ReportContext.addStep("Clicking on Setup link");
            vscodePage.clickSetupLink();
            Assert.assertTrue(vscodePage.isSetupPageLoaded(),
                    "Setup page failed to load");
            ReportContext.addStep("Successfully navigated to Setup documentation");

            // Step 4: Click on Overview link
            ReportContext.addStep("Clicking on Overview link");
            vscodePage.clickOverviewLink();
            Assert.assertTrue(vscodePage.isOverviewPageLoaded(),
                    "Overview page failed to load");
            ReportContext.addStep("Successfully navigated to Overview section");

            // Mark test as passed
            context.setTestStatus(TestContext.TestStatus.PASSED);
            ReportContext.addStep("Test completed successfully", "PASS");

        } catch (Exception e) {
            // Handle failure
            context.setTestStatus(TestContext.TestStatus.FAILED);
            context.setErrorMessage(e.getMessage());
            ReportContext.addStep("Test failed: " + e.getMessage(), "FAIL");
            throw e;
        }
    }
}
