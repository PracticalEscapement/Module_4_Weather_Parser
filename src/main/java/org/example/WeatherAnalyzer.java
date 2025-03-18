package org.example;
import java.time.Month;
import java.util.*;

public interface WeatherAnalyzer {
    // Get the average temperature for specified month
    static double averageTemperature(List<WeatherData> data, Month month) {
        return Math.round(data.stream()
                .filter(w -> w.date().getMonth() == month)
                .mapToDouble(WeatherData::temperature)
                .average()
                .orElse(Double.NaN));
    }

    // Get the list of hot days
    static List<WeatherData> hotDays(List<WeatherData> data, double threshold) {
        return data.stream()
                .filter(w -> w.temperature() > threshold)
                .toList();
    }

    // Based on temperature return string representation ex. "hot", "cold", "mild"
    static String classifyTemperature(double temp) {
        return switch ((int) temp / 10) {
            case 3, 4 -> "Hot";    // 30-49°C
            case 2 -> "Warm";      // 20-29°C
            case 1 -> "Mild";      // 10-19°C
            default -> "Cold";     // Below 10°C
        };
    }



}
