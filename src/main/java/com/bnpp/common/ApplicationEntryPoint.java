package com.bnpp.common;

import com.bnpp.common.models.DataLine;
import com.bnpp.common.service.FileWriterService;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ApplicationEntryPoint {

    public static void main(String[] args) {
        assertFilesProvided(args);

        List<DataLine> dataLines = parseDataLinesInFiles(args);

        new FileWriterService().write(dataLines);
    }

    private static List<DataLine> parseDataLinesInFiles(String[] args) {
        ParsingService parsingService = new ParsingService();

        List<DataLine> result = new ArrayList<>();

        Arrays.stream(args).forEach(filePath -> {
            result.addAll(parsingService.parse(Path.of(filePath)));
        });
        return result;
    }

    private static void assertFilesProvided(String[] args) {
        if (args == null || args.length == 0) {
            throw new IllegalArgumentException("No files provided for aggregation");
        }
    }
}