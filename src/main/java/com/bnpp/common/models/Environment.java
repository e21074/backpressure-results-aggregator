package com.bnpp.common.models;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;

public enum Environment {
    PRODUCTION("SVC2RUXGPFDBLENSP@bnpparibas.com"),
    STAGING("SVC2RUXGPFDBLENSS@bnpparibas.com"),
    UNRECOGNIZED(null);

    private final String sourceEmail;

    Environment(String sourceEmail) {
        this.sourceEmail = sourceEmail;
    }

    public static Environment fromString(String s) {
        return Arrays
                .stream(values())
                .filter(env -> env.getSourceEmail().equalsIgnoreCase(s))
                .findFirst()
                .orElse(UNRECOGNIZED);
    }

    public String getSourceEmail() {
        return sourceEmail;
    }
}