package org.example;
import java.util.*;

import java.io.IOException;

// Main class to start the weather analyzer
public class Main {
    public static void main(String[] args) throws IOException {
        try {
            // Load Weather Data for the Year
            List<WeatherData> weatherData = WeatherParser.parseData("src/main/java/org/example/weatherData.csv");
            System.out.println("Successfully loaded " + weatherData.size() + " weather records.");


        } catch (IOException e) {
            System.err.println("Error reading the CSV file: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.err.println("Invalid file path: " + e.getMessage());
        }
    }
}