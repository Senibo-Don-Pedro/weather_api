package com.pm.weatherapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.ToString;

import java.util.List;

public class WeatherData {

    public record Coord(
            String lat,
            String lon
    ) {
    }

    public record Weather(
            int id,
            String main,
            String description,
            String icon

    ) {
    }

    public record Main(
            double temp,
            @JsonProperty("feels_like") double feelsLike,
            @JsonProperty("temp_min") double tempMin,
            @JsonProperty("temp_max") double tempMax,
            int pressure,
            int humidity,
            @JsonProperty("sea_level") Integer seaLevel,  // Can be null
            @JsonProperty("grnd_level") Integer grndLevel // Can be null
    ) {
    }

    public record Wind(double speed, int deg, double gust) {
    }

    public record Clouds(int all) {
    }

    public record Sys(String country, int sunrise, int sunset) {
    }


    public record Response(
            Coord coord,
            List<Weather> weather,
            String base,
            Main main,
            int visibility,
            Wind wind,
            Clouds clouds,
            int dt,
            Sys sys,
            int timezone,
            int id,
            String name,
            int cod
    ) {
    }

}
