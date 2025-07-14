package com.test.framework.context;

import java.util.HashMap;
import java.util.Map;

public class ScenarioContext {
    private Map<String, Object> scenarioData;
    private Map<String, Long> stepStartTimes;
    private Map<String, Long> stepEndTimes;
    private String currentStep;
    private static ThreadLocal<ScenarioContext> instance = new ThreadLocal<>();

    private ScenarioContext() {
        scenarioData = new HashMap<>();
        stepStartTimes = new HashMap<>();
        stepEndTimes = new HashMap<>();
    }

    public static ScenarioContext getInstance() {
        if (instance.get() == null) {
            instance.set(new ScenarioContext());
        }
        return instance.get();
    }

    public void setContext(String key, Object value) {
        scenarioData.put(key, value);
    }

    public Object getContext(String key) {
        return scenarioData.get(key);
    }

    public boolean containsKey(String key) {
        return scenarioData.containsKey(key);
    }

    public void clear() {
        scenarioData.clear();
        stepStartTimes.clear();
        stepEndTimes.clear();
        currentStep = null;
    }

    public void startStep(String stepName) {
        currentStep = stepName;
        stepStartTimes.put(stepName, System.currentTimeMillis());
    }

    public void endStep(String stepName) {
        stepEndTimes.put(stepName, System.currentTimeMillis());
    }

    public long getStepDuration(String stepName) {
        Long startTime = stepStartTimes.get(stepName);
        Long endTime = stepEndTimes.get(stepName);

        if (startTime != null && endTime != null) {
            return endTime - startTime;
        }
        return -1;
    }

    public String getCurrentStep() {
        return currentStep;
    }

    public Map<String, Long> getAllStepDurations() {
        Map<String, Long> durations = new HashMap<>();
        for (String step : stepStartTimes.keySet()) {
            durations.put(step, getStepDuration(step));
        }
        return durations;
    }

    public void cleanup() {
        clear();
        instance.remove();
    }
}
