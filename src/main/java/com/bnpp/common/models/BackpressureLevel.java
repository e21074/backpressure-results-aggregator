package com.bnpp.common.models;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;

public enum BackpressureLevel {

    MEDIUM(9),
    HIGH(11),
    CRITICAL(7),
    UNRECOGNIZED(3);

    private final int amountOfSpacesInOutputFileAfter;

    BackpressureLevel(int amountOfSpacesInOutputFileAfter) {
        this.amountOfSpacesInOutputFileAfter = amountOfSpacesInOutputFileAfter;
    }

    public static BackpressureLevel fromString(String s) {
        return Arrays.stream(values())
                .filter(backpressureLevel -> StringUtils.equalsIgnoreCase(backpressureLevel.name(), s))
                .findFirst()
                .orElse(UNRECOGNIZED);
    }


    @Override
    public String toString() {
        return this.name() + " ".repeat(amountOfSpacesInOutputFileAfter);
    }
}