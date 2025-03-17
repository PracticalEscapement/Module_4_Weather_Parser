package org.example;
import java.time.Month;
import java.util.*;

public interface WeatherAnalyzer {
    static double averageTemperature(List<WeatherData> data, Month month) {
        return Math.round(data.stream()
                .filter(w -> w.date().getMonth() == month)
                .mapToDouble(WeatherData::temperature)
                .average()
                .orElse(Double.NaN));
    }
}
