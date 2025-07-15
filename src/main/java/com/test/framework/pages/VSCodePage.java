package com.test.framework.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import java.util.List;

public class VSCodePage extends BasePage {

    // Locators
    private final By docsTab = By.cssSelector("a[href='/docs']");
    private final By setupLinks = By.cssSelector("a[href*='setup'], a[href*='getting-started']");
    private final By docsContent = By.cssSelector(".docs-content, .body");
    private final By mainContent = By.id("main");
    private final By sideNav = By.cssSelector(".Pane.vertical");

    // Overview locators
    private final By overviewLink = By.xpath("//a[contains(text(), 'Overview') or contains(@href, 'overview')]");
    private final By overviewContent = By.cssSelector(".content[data-page-title*='Overview'], #main");
    private final By overviewHeader = By.xpath("//h1[contains(text(), 'Overview')]");

    public VSCodePage(WebDriver driver) {
        super(driver);
    }

    public void navigateToVSCodeSite() {
        driver.get("https://code.visualstudio.com/");
        waitForPageLoad();
    }

    public void clickDocsTab() {
        // Wait for docs tab and click
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(docsTab));
        clickWithRetry(element);
        waitForPageLoad();
    }

    public void clickSetupLink() {
        // Wait for docs content to load
        wait.until(ExpectedConditions.presenceOfElementLocated(docsContent));

        // Find all potential setup links
        List<WebElement> links = driver.findElements(setupLinks);
        WebElement setupLink = links.stream()
                .filter(e -> {
                    String text = e.getText().toLowerCase();
                    String href = e.getAttribute("href").toLowerCase();
                    return (text.contains("setup") || text.contains("getting started")) &&
                            (href.contains("setup") || href.contains("getting-started"));
                })
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Setup link not found"));

        // Click the setup link
        clickWithRetry(setupLink);
        waitForPageLoad();
    }

    public void clickOverviewLink() {
        // Wait for docs content to load
        wait.until(ExpectedConditions.presenceOfElementLocated(docsContent));

        // Find and click Overview link
        List<WebElement> links = driver.findElements(overviewLink);
        WebElement overview = links.stream()
                .filter(e -> {
                    String text = e.getText().toLowerCase();
                    return text.contains("overview");
                })
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Overview link not found"));

        // Click the overview link
        clickWithRetry(overview);
        waitForPageLoad();
    }

    public boolean isDocsPageLoaded() {
        try {
            return wait.until(ExpectedConditions.and(
                    ExpectedConditions.presenceOfElementLocated(mainContent),
                    ExpectedConditions.presenceOfElementLocated(sideNav))) != null;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isSetupPageLoaded() {
        try {
            String url = driver.getCurrentUrl().toLowerCase();
            return (url.contains("setup") || url.contains("getting-started")) &&
                    wait.until(ExpectedConditions.presenceOfElementLocated(docsContent)) != null;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isOverviewPageLoaded() {
        try {
            String url = driver.getCurrentUrl().toLowerCase();
            return url.contains("overview") &&
                    (wait.until(ExpectedConditions.or(
                            ExpectedConditions.presenceOfElementLocated(overviewContent),
                            ExpectedConditions.presenceOfElementLocated(overviewHeader))) != null);
        } catch (Exception e) {
            return false;
        }
    }

    private void clickWithRetry(WebElement element) {
        try {
            // Try scrolling to element first
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
            Thread.sleep(500);

            // Try normal click
            element.click();
        } catch (Exception e1) {
            try {
                // Try Actions click
                new Actions(driver)
                        .moveToElement(element)
                        .click()
                        .perform();
            } catch (Exception e2) {
                // Try JavaScript click as last resort
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
            }
        }
    }

    @Override
    protected void waitForPageLoad() {
        // Wait for document ready state
        wait.until(webDriver -> ((JavascriptExecutor) webDriver)
                .executeScript("return document.readyState").equals("complete"));

        // Wait for main content
        wait.until(ExpectedConditions.presenceOfElementLocated(mainContent));

        // Additional wait for dynamic content
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
