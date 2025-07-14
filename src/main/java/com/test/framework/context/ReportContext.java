package com.test.framework.context;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReportContext {
    private static ThreadLocal<ReportContext> instance = new ThreadLocal<>();
    private List<TestStep> testSteps;
    private Map<String, String> testMetadata;
    private List<String> screenshots;
    private List<String> logs;
    private Map<String, Object> customData;

    public static class TestStep {
        private String name;
        private String description;
        private String status;
        private long duration;
        private String screenshot;
        private String errorMessage;

        public TestStep(String name, String description) {
            this.name = name;
            this.description = description;
        }

        // Getters and setters
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public long getDuration() {
            return duration;
        }

        public void setDuration(long duration) {
            this.duration = duration;
        }

        public String getScreenshot() {
            return screenshot;
        }

        public void setScreenshot(String screenshot) {
            this.screenshot = screenshot;
        }

        public String getErrorMessage() {
            return errorMessage;
        }

        public void setErrorMessage(String errorMessage) {
            this.errorMessage = errorMessage;
        }
    }

    private ReportContext() {
        testSteps = new ArrayList<>();
        testMetadata = new HashMap<>();
        screenshots = new ArrayList<>();
        logs = new ArrayList<>();
        customData = new HashMap<>();
    }

    public static ReportContext getInstance() {
        if (instance.get() == null) {
            instance.set(new ReportContext());
        }
        return instance.get();
    }

    public void addTestStep(TestStep step) {
        testSteps.add(step);
    }

    public List<TestStep> getTestSteps() {
        return testSteps;
    }

    public void addMetadata(String key, String value) {
        testMetadata.put(key, value);
    }

    public Map<String, String> getMetadata() {
        return testMetadata;
    }

    public void addScreenshot(String screenshotPath) {
        screenshots.add(screenshotPath);
    }

    public List<String> getScreenshots() {
        return screenshots;
    }

    public void addLog(String logMessage) {
        logs.add(logMessage);
    }

    public List<String> getLogs() {
        return logs;
    }

    public void addCustomData(String key, Object value) {
        customData.put(key, value);
    }

    public Object getCustomData(String key) {
        return customData.get(key);
    }

    public void clearContext() {
        testSteps.clear();
        testMetadata.clear();
        screenshots.clear();
        logs.clear();
        customData.clear();
    }

    public void cleanup() {
        clearContext();
        instance.remove();
    }
}
