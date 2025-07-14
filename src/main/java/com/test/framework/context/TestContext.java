package com.test.framework.context;

import org.openqa.selenium.WebDriver;
import java.util.HashMap;
import java.util.Map;
import java.time.Duration;
import java.time.LocalDateTime;

public class TestContext {
    private static ThreadLocal<TestContext> instance = new ThreadLocal<>();
    private WebDriver driver;
    private String testName;
    private String testDescription;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Map<String, Object> testData;
    private Map<String, Object> scenarioContext;
    private String currentPage;
    private TestStatus testStatus;
    private String errorMessage;
    private byte[] screenshot;
    private Map<String, Duration> pageLoadTimes;
    private Map<String, String> testParameters;

    public enum TestStatus {
        PASSED, FAILED, SKIPPED, IN_PROGRESS
    }

    private TestContext() {
        testData = new HashMap<>();
        scenarioContext = new HashMap<>();
        pageLoadTimes = new HashMap<>();
        testParameters = new HashMap<>();
        startTime = LocalDateTime.now();
        testStatus = TestStatus.IN_PROGRESS;
    }

    public static TestContext getInstance() {
        if (instance.get() == null) {
            instance.set(new TestContext());
        }
        return instance.get();
    }

    public void reset() {
        testData.clear();
        scenarioContext.clear();
        pageLoadTimes.clear();
        testParameters.clear();
        startTime = LocalDateTime.now();
        endTime = null;
        errorMessage = null;
        screenshot = null;
        testStatus = TestStatus.IN_PROGRESS;
    }

    // Driver Management
    public WebDriver getDriver() {
        return driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    // Test Information
    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public String getTestDescription() {
        return testDescription;
    }

    public void setTestDescription(String testDescription) {
        this.testDescription = testDescription;
    }

    // Test Data Management
    public void setTestData(String key, Object value) {
        testData.put(key, value);
    }

    public Object getTestData(String key) {
        return testData.get(key);
    }

    // Scenario Context Management
    public void setScenarioContext(String key, Object value) {
        scenarioContext.put(key, value);
    }

    public Object getScenarioContext(String key) {
        return scenarioContext.get(key);
    }

    // Page Navigation Tracking
    public String getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(String pageName) {
        this.currentPage = pageName;
    }

    // Test Status Management
    public TestStatus getTestStatus() {
        return testStatus;
    }

    public void setTestStatus(TestStatus status) {
        this.testStatus = status;
        if (status != TestStatus.IN_PROGRESS) {
            endTime = LocalDateTime.now();
        }
    }

    // Error Handling
    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String message) {
        this.errorMessage = message;
    }

    // Screenshot Management
    public byte[] getScreenshot() {
        return screenshot;
    }

    public void setScreenshot(byte[] screenshot) {
        this.screenshot = screenshot;
    }

    // Performance Tracking
    public void addPageLoadTime(String pageName, Duration loadTime) {
        pageLoadTimes.put(pageName, loadTime);
    }

    public Map<String, Duration> getPageLoadTimes() {
        return pageLoadTimes;
    }

    // Test Parameters
    public void setParameter(String key, String value) {
        testParameters.put(key, value);
    }

    public String getParameter(String key) {
        return testParameters.get(key);
    }

    // Test Duration
    public Duration getTestDuration() {
        LocalDateTime end = endTime != null ? endTime : LocalDateTime.now();
        return Duration.between(startTime, end);
    }

    // Context Cleanup
    public void cleanup() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
        instance.remove();
    }
}
