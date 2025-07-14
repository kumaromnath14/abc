package com.test.framework.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.test.framework.config.Config;

public class HomePage extends BasePage {
    // Locators
    private final By menTab = By.xpath("//a[@href='/shop/men' or contains(text(),'MEN')]");
    private final By womenTab = By.xpath("//a[@href='/shop/women' or contains(text(),'WOMEN')]");
    private final String BASE_URL = Config.getBaseUrl();

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void navigateToHomePage() {
        driver.get(BASE_URL);
        // Wait for page load
        try {
            Thread.sleep(3000); // Wait for initial load
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void clickMenTab() {
        // Wait and click
        wait.until(d -> isElementDisplayed(menTab));
        click(menTab);
    }

    public void clickWomenTab() {
        // Wait and click
        wait.until(d -> isElementDisplayed(womenTab));
        click(womenTab);
    }

    public boolean isMenTabDisplayed() {
        try {
            return wait.until(d -> isElementDisplayed(menTab));
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isWomenTabDisplayed() {
        try {
            return wait.until(d -> isElementDisplayed(womenTab));
        } catch (Exception e) {
            return false;
        }
    }

    // Method to navigate and click Men tab
    public void navigateAndClickMenTab() {
        navigateToHomePage();
        clickMenTab();
    }

    // Method to navigate through sections
    public void navigateThroughSections() {
        navigateToHomePage();
        clickMenTab();
        // Wait for men's section to load
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        clickWomenTab();
    }
}
