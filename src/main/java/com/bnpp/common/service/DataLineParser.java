package com.bnpp.common.service;

import com.bnpp.common.models.BackpressureLevel;
import com.bnpp.common.models.DataLine;
import com.bnpp.common.models.Environment;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

public class DataLineParser {

    private final String FIRST_STRING_STARTING_VERSION = "Attention: the stream Source: ";
    private final String SECOND_STRING_STARTING_VERSION = "Attention: the stream ";

    public DataLine parseNormalizedString(String s) {
        String[] tokens = s.split("\t");
        Assert.state(tokens.length == 4, "DataLine does not contain exactly 4 columns - source email, backpressure level, timestamp and streams");
        DataLine dataLine = new DataLine();
        dataLine.setEnvironment(Environment.fromString(tokens[0]));
        dataLine.setBackpressureLevel(BackpressureLevel.fromString(tokens[1]));
        dataLine.setBackpressureOccurredAt(tokens[2]);
        dataLine.setStreamNames(parseStreamNames(tokens[3]));
        return dataLine;
    }

    // TODO: refactor
    public List<String> parseStreamNames(String string) {
        if (string.startsWith(FIRST_STRING_STARTING_VERSION)) {
            return createStreamsList(string, FIRST_STRING_STARTING_VERSION);
        } else if (string.startsWith(SECOND_STRING_STARTING_VERSION)) {
            return createStreamsList(string, SECOND_STRING_STARTING_VERSION);
        } else {
            return new ArrayList<>();
        }
    }

    private List<String> createStreamsList(String string, String startOfTheString) {
        String substringWithStreamNameAsFirstWord = string.substring(startOfTheString.length());

        String finalStreamName;

        if (substringWithStreamNameAsFirstWord.contains(" ")) {
            finalStreamName = substringWithStreamNameAsFirstWord.substring(0, substringWithStreamNameAsFirstWord.indexOf(" "));
        } else {
            return new ArrayList<>();
        }

        List<String> streamsFound = new ArrayList<>();
        streamsFound.add(finalStreamName);
        return streamsFound;
    }
}