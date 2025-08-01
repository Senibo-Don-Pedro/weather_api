package com.pm.weatherapi.dto;

public record WeatherMapper(
        String cityName,
        double temperature,
        String description,
        int humidity,
        double windSpeed
){

    public static WeatherMapper toWeatherMapper(WeatherData.Response response) {
        WeatherData.Weather weather = response.weather().getFirst();

        return new WeatherMapper(
                response.name(),
                response.main().temp(),
                weather.description(),
                response.main().humidity(),
                response.wind().speed()
        );
    }
}
