package com.pm.weatherapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Weather information for a city")
public record WeatherMapper(

        @Schema(description = "City name", example = "London")
        String cityName,

        @Schema(description = "Temperature in Celsius", example = "15.5")
        double temperature,

        @Schema(description = "Weather description", example = "clear sky")
        String description,

        @Schema(description = "Humidity percentage", example = "72")
        int humidity,

        @Schema(description = "Wind speed in m/s", example = "3.2")
        double windSpeed
) {

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
