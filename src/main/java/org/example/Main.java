package org.example;
import java.time.Month;
import java.util.*;

import java.io.IOException;

// Main class to start the weather analyzer
public class Main {
    public static void main(String[] args) throws IOException {
        try {
            // Load Weather Data for the Year
            List<WeatherData> weatherData = WeatherParser.parseData("src/main/java/org/example/weatherData.csv");
            System.out.println("Successfully loaded " + weatherData.size() + " weather records.");

            // Get the average temp for specified month
            double averageTempJanuary = WeatherAnalyzer.averageTemperature(weatherData, Month.JANUARY);
            System.out.println("Average Temperature January: " + averageTempJanuary + " C");

            // Get the number of hot days aka. temp above specified threshold
            // 27c = 80f
            System.out.println("Number of Hot Days: " + WeatherAnalyzer.hotDays(weatherData, 28).size());

            // Randomly select 10 data records and use the classifyTemperature method
            List<WeatherData> dataCopy = new ArrayList<>(List.copyOf(weatherData));
            Collections.shuffle(dataCopy);
            List<WeatherData> randomlySelected = new ArrayList<>(dataCopy.subList(0, 10));
            randomlySelected.forEach(data ->
                    System.out.println("Temperature: " + data.temperature() + "C -> " + WeatherAnalyzer.classifyTemperature(data.temperature()))
            );

            // Get the number of rainy days
            System.out.println("Number of Rainy Days: " + WeatherAnalyzer.countRainyDays(weatherData));

            // Using text block get the report for the month
            System.out.println("\n\n");
            System.out.println(WeatherAnalyzer.generateReport(weatherData, Month.MARCH));


        } catch (IOException e) {
            System.err.println("Error reading the CSV file: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.err.println("Invalid file path: " + e.getMessage());
        }
    }
}