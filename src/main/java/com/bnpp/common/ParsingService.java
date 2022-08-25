package com.bnpp.common;

import com.bnpp.common.models.DataLine;
import com.bnpp.common.service.DataLineParser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class ParsingService {

    private final DataLineParser dataLineParser = new DataLineParser();

    public List<DataLine> parse(Path source) {
        try {
            List<String> lines = Files.readAllLines(source);

            List<DataLine> response = new ArrayList<>();

            DataLine currentDataLine = null;

            for (int i = 0; i < lines.size(); i++) {

                try {
                    String normalizedLine = lines.get(i).trim();

                    if (normalizedLine.isEmpty()) {
                        continue;
                    }

                    if (!normalizedLine.startsWith("Attention: the stream")) {
                        if (currentDataLine != null) {
                            response.add(currentDataLine);
                        }
                        currentDataLine = dataLineParser.parseNormalizedString(normalizedLine);
                    } else {
                        List<String> additionalStreams = dataLineParser.parseStreamNames(normalizedLine);
                        currentDataLine.addStreamNames(additionalStreams);
                    }
                } catch (Exception e) {
                    System.out.println("Exception occurred during processing line : " + i);
                    throw e;
                }
            }
            return response;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}