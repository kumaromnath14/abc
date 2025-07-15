package com.test.framework.context;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;

public class ReportContext {
    private static ThreadLocal<ReportContext> instance = new ThreadLocal<>();
    private List<TestStep> testSteps;

    private ReportContext() {
        testSteps = new ArrayList<>();
    }

    public static ReportContext getInstance() {
        if (instance.get() == null) {
            instance.set(new ReportContext());
        }
        return instance.get();
    }

    public static void addStep(String description) {
        getInstance().testSteps.add(new TestStep(description));
    }

    public static void addStep(String description, String status) {
        getInstance().testSteps.add(new TestStep(description, status));
    }

    public List<TestStep> getTestSteps() {
        return testSteps;
    }

    public void clearSteps() {
        testSteps.clear();
    }

    public static class TestStep {
        private String description;
        private LocalDateTime timestamp;
        private String status;

        public TestStep(String description) {
            this.description = description;
            this.timestamp = LocalDateTime.now();
            this.status = "INFO";
        }

        public TestStep(String description, String status) {
            this.description = description;
            this.timestamp = LocalDateTime.now();
            this.status = status;
        }

        public String getDescription() {
            return description;
        }

        public LocalDateTime getTimestamp() {
            return timestamp;
        }

        public String getStatus() {
            return status;
        }
    }
}
