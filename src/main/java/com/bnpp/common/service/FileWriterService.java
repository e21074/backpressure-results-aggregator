package com.bnpp.common.service;

import com.bnpp.common.models.DataLine;
import com.bnpp.common.models.Environment;
import com.bnpp.common.util.BPComparator;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FileWriterService {

    private final PrintWriter printWriter;

    public FileWriterService() {
        try {
            String targetFilePath = System.getProperty("targetFilePath");
            if (targetFilePath == null) {
                throw new IllegalArgumentException("Set -DtargetFilePath property");
            }
            printWriter = new PrintWriter(targetFilePath);

            Files.write(Path.of(targetFilePath) ,new byte[0], StandardOpenOption.TRUNCATE_EXISTING);

        } catch (IOException e) {
            throw new RuntimeException("non existed target file path provided");
        }
    }

    public void write(List<DataLine> dataLines) {

        Map<Environment, List<DataLine>> collectedByEnvs = dataLines.stream().collect(Collectors.groupingBy(DataLine::getEnvironment));

        collectedByEnvs.forEach(this::writeEnvLines);
    }

    private void writeEnvLines(Environment environment, List<DataLine> dataLines) {
        printWriter.println(environment.name());
        printWriter.println();

        dataLines.stream().forEach(dataLine -> {
            printWriter.println(getCurrentTimestamp(dataLine) + "        " + dataLine.getBackpressureLevel() + "        " + dataLine.getStreamNames());
        });

        printWriter.println();
        printWriter.println();
    }

    private String getCurrentTimestamp(DataLine dataLine) {
        if (Character.isAlphabetic(dataLine.getBackpressureOccurredAt().charAt(0))) {
            if (dataLine.getBackpressureOccurredAt().trim().length() == 7) {
                return "0" + dataLine.getBackpressureOccurredAt();
            } else {
                return dataLine.getBackpressureOccurredAt();
            }
        } else {
            String result = LocalDateTime.now().getDayOfWeek().name().substring(0, 2);
            if (dataLine.getBackpressureOccurredAt().trim().length() == 4) {
                return result + " 0" + dataLine.getBackpressureOccurredAt();
            } else {
                return result + " " + dataLine.getBackpressureOccurredAt();
            }
        }
    }
}