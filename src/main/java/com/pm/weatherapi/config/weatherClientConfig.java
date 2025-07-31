package com.pm.weatherapi.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class weatherClientConfig {

    @Bean
    public RestClient restClient(
            @Value("${openweather.api.base-url}")
            String baseUrl) {
        return RestClient.builder()
                .baseUrl(baseUrl)
                .defaultHeader("Accept", "application/json") // Tell the API we want JSON response
                .defaultHeader("User-Agent", "WeatherApp/1.0") // Good practice to identify your app
                .build();
    }
}
