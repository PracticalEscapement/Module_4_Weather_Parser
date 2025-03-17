package org.example;

import java.time.LocalDate;

public record WeatherData(LocalDate date, double temperature, double humidity, double precipitation) {}
