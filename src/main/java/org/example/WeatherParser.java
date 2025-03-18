package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;


public interface WeatherParser {

    /**
     * Parses the CSV file at the given file path and returns a list of WeatherData records.
     *
     * @param filePath the path to the CSV file.
     * @return a list of WeatherData records.
     * @throws IOException if an I/O error occurs reading the file.
     * @throws IllegalArgumentException if the file is not found.
     */
    static List<WeatherData> parseData(String filePath) throws IOException {
        Path path = Path.of(filePath);
        if (!Files.exists(path)) {
            throw new IllegalArgumentException("File not found: " + filePath);
        }
        // Log the absolute path for verification
        System.out.println("Reading CSV file from: " + path.toAbsolutePath());

        try (var lines = Files.lines(path)) {
            return lines.skip(1) // Skip header line
                    .map(line -> line.split(","))
                    .map(parts -> {
                        if (parts.length < 4) {
                            throw new RuntimeException("Invalid CSV format in line: " + String.join(",", parts));
                        }
                        return new WeatherData(
                                LocalDate.parse(parts[0].strip()), // Date
                                Double.parseDouble(parts[1].strip()), // Temperature
                                Double.parseDouble(parts[2].strip()), // Humidity
                                Double.parseDouble(parts[3].strip())  // Precipitation
                        );
                    })
                    .collect(Collectors.toList());
        }
    }
}
