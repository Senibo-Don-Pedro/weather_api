package com.pm.weatherapi;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
@OpenAPIDefinition(
        info = @Info(
                title = "Weather API",
                version = "1.0",
                description = "A comprehensive REST API that provides real-time weather information for cities worldwide. " +
                        "This API integrates with OpenWeatherMap to fetch current weather data including temperature, " +
                        "humidity, wind speed, and weather conditions. Features intelligent caching for optimal performance."
        )
)
public class WeatherapiApplication {

    public static void main(String[] args) {
        SpringApplication.run(WeatherapiApplication.class, args);
    }

}
