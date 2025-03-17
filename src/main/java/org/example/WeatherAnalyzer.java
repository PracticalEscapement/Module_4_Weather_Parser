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

    // Get the hot days
    static List<WeatherData> hotDays(List<WeatherData> data, double threshold) {
        return data.stream()
                .filter(w -> w.temperature() > threshold)
                .toList();
    }


}
