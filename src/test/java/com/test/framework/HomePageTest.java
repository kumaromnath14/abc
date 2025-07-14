package com.test.framework;

import com.test.framework.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HomePageTest extends BaseTest {

    @Test
    public void testNavigateToMenSection() {
        HomePage homePage = new HomePage(driver);

        // Navigate to AJIO homepage
        homePage.navigateToHomePage();

        // Verify Men tab is displayed
        Assert.assertTrue(homePage.isMenTabDisplayed(), "Men tab should be displayed on homepage");

        // Click on Men tab
        homePage.clickMenTab();

        // Verify the URL contains men section
        Assert.assertTrue(driver.getCurrentUrl().toLowerCase().contains("men"),
                "URL should contain 'men' after clicking Men tab");
    }
}
