package org.example;
import java.time.Month;
import java.util.*;

/**
 * The WeatherAnalyzer interface provides methods to analyze weather data.
 * It includes methods to calculate average temperature, identify hot days,
 * classify temperature, count rainy days, and generate weather reports.
 */
public interface WeatherAnalyzer {

    /**
     * Calculates the average temperature for a specified month.
     *
     * @param data  The list of weather data records.
     * @param month The month to calculate the average temperature for.
     * @return The average temperature for the specified month.
     */
    static double averageTemperature(List<WeatherData> data, Month month) {
        return Math.round(data.stream()
                .filter(w -> w.date().getMonth() == month)
                .mapToDouble(WeatherData::temperature)
                .average()
                .orElse(Double.NaN));
    }

    /**
     * Retrieves a list of hot days where the temperature exceeds the specified threshold.
     *
     * @param data      The list of weather data records.
     * @param threshold The temperature threshold to classify a day as hot.
     * @return A list of WeatherData objects representing hot days.
     */
    static List<WeatherData> hotDays(List<WeatherData> data, double threshold) {
        return data.stream()
                .filter(w -> w.temperature() > threshold)
                .toList();
    }

    /**
     * Classifies the temperature into categories such as "Hot", "Warm", "Mild", or "Cold".
     *
     * @param temp The temperature to classify.
     * @return A string representing the temperature classification.
     */
    static String classifyTemperature(double temp) {
        return switch ((int) temp / 10) {
            case 3, 4 -> "Hot";    // 30-49°C
            case 2 -> "Warm";      // 20-29°C
            case 1 -> "Mild";      // 10-19°C
            default -> "Cold";     // Below 10°C
        };
    }

    /**
     * Counts the number of rainy days in the provided weather data.
     *
     * @param data The list of weather data records.
     * @return The number of rainy days.
     */
    static long countRainyDays(List<WeatherData> data) {
        return data.stream()
                .filter(w -> w.precipitation() > 0)
                .count();
    }

    /**
     * Generates a weather report for a given month based on the yearly data.
     *
     * @param data  The list of yearly weather records.
     * @param month The month to analyze.
     * @return A formatted report as a string.
     */
    static String generateReport(List<WeatherData> data, Month month) {
        // Filter data for the specified month
        List<WeatherData> filtered = data.stream()
                .filter(w -> w.date().getMonth() == month)
                .toList();

        // Compute average temperature
        double avgTemp = filtered.stream()
                .mapToDouble(WeatherData::temperature)
                .average()
                .orElse(Double.NaN); // Return NaN if no data

        // Count rainy days (precipitation > 0)
        long rainyDays = filtered.stream()
                .filter(w -> w.precipitation() > 0)
                .count();

        return """
                Weather Report for %s:
                ========================
                Average Temperature: %.2f°C
                Rainy Days: %d
                """.formatted(
                month, avgTemp, rainyDays
        );
    }
}
