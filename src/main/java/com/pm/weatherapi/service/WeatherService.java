package com.pm.weatherapi.service;

import com.pm.weatherapi.dto.WeatherData;
import jakarta.validation.constraints.NotBlank;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.Map;

@Slf4j
@Service
public class WeatherService {

    public final String appId;
    public final String defaultUnits;
    private final RestClient restClient;

    public WeatherService(RestClient restClient,
                          @Value("${openweather.api.key}")
                          String appId,
                          @Value("${openweather.api.default-units}")
                          String defaultUnits) {
        this.restClient = restClient;
        this.appId = appId;
        this.defaultUnits = defaultUnits;
    }

    public WeatherData.Response getCurrentWeather(String city) {
        try {
            log.info("Get current weather for city: {}", city);
            WeatherData.Response response =  restClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .queryParam("q", city)
                            .queryParam("units", defaultUnits)
                            .queryParam("appId", appId)
                            .build())
                    .retrieve()
                    .body(WeatherData.Response.class);

            log.info("Gotten weather data for city: {}, = {}", city, Map.of(
                    "city", response.name(),
                    "weather", response.weather()
            ));

            return response;
        } catch (Exception e) {
            log.error("Error getting weather data for city: {}", city, e.getMessage());
            throw new RuntimeException("Unable to fetch weather data for " + city, e);
        }
    }
}
