package com.pm.weatherapi.service;

import com.pm.weatherapi.dto.WeatherData;
import com.pm.weatherapi.dto.WeatherMapper;
import com.pm.weatherapi.exceptions.CityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;

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

    /**
     * Use @Cacheable to automatically cache the results of this method.
     * The cache key is generated based on the city name, which is passed as the argument.
     */
    @Cacheable(value = "weather", key = "#city.toLowerCase()")
    public WeatherMapper getCurrentWeather(String city) {
        try {
            log.info("Get current weather for city: {}", city);

            WeatherData.Response response = restClient.get()
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

            return WeatherMapper.toWeatherMapper(response);


        } catch (HttpClientErrorException e) {
            if (e.getStatusCode().value() == 404) {
                log.warn("City not found in OpenWeather API: {}", city);
                throw new CityNotFoundException(String.format("City '%s' not found", city));
            }
            log.error("Client error while fetching weather for {}: {}", city, e.getMessage());
            throw e;
        } catch (RestClientException e) {
            log.error("Error while calling weather service: {}", e.getMessage());
//            throw new RuntimeException("Weather service is temporarily unavailable", e);
            
            throw new RestClientException("Weather service is temporarily unavailable");
        }
    }
}
