package com.bnpp.common.models;

import java.util.ArrayList;
import java.util.List;

public class DataLine {

    private Environment environment;
    private BackpressureLevel backpressureLevel;
    private String backpressureOccurredAt;
    private List<String> streamNames;

    public Environment getEnvironment() {
        return environment;
    }

    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    public String getBackpressureOccurredAt() {
        return backpressureOccurredAt;
    }

    public void setBackpressureOccurredAt(String backpressureOccurredAt) {
        this.backpressureOccurredAt = backpressureOccurredAt;
    }

    public List<String> getStreamNames() {
        return streamNames;
    }

    public void setStreamNames(List<String> streamNames) {
        this.streamNames = streamNames;
    }

    public void addStreamNames(List<String> streamNames) {
        if (this.streamNames == null) {
            this.streamNames = new ArrayList<>();
        }
        this.streamNames.addAll(streamNames);
    }


    public BackpressureLevel getBackpressureLevel() {
        return backpressureLevel;
    }

    public void setBackpressureLevel(BackpressureLevel backpressureLevel) {
        this.backpressureLevel = backpressureLevel;
    }
}