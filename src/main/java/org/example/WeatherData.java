package org.example;

import java.time.LocalDate;

/**
 * @param date          The date yyyy-mm-dd
 * @param temperature   The temperature in degrees Celsius.
 * @param humidity      The humidity percentage.
 * @param precipitation The precipitation in millimeters.
 */
public record WeatherData(LocalDate date, double temperature, double humidity, double precipitation) {}
