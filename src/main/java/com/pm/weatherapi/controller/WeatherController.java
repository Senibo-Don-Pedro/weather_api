package com.pm.weatherapi.controller;

import com.pm.weatherapi.dto.ApiResponse;
import com.pm.weatherapi.dto.WeatherMapper;
import com.pm.weatherapi.service.WeatherService;
import jakarta.validation.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/weather")
@Validated
public class WeatherController {

    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping
    public ApiResponse<WeatherMapper>  getWeather(@NotBlank @RequestParam String city) {
        WeatherMapper weather = weatherService.getCurrentWeather(city);
        return ApiResponse.success(weather,"Successfully fetched weather data");
    }
}
