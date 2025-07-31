package com.pm.weatherapi.controller;

import com.pm.weatherapi.dto.WeatherData;
import com.pm.weatherapi.service.WeatherService;
import jakarta.validation.constraints.NotBlank;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/weather")
public class WeatherController {

    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping
    public WeatherData.Response getWeather(@NotBlank @RequestParam String city) {
        return weatherService.getCurrentWeather(city);
    }
}
