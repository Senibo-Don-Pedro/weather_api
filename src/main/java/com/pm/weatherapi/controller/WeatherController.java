//package com.pm.weatherapi.controller;
//
//import com.pm.weatherapi.dto.ApiResponse;
//import com.pm.weatherapi.dto.WeatherMapper;
//import com.pm.weatherapi.service.WeatherService;
//import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.Parameter;
//import io.swagger.v3.oas.annotations.tags.Tag;
//import jakarta.validation.constraints.NotBlank;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//
//@RestController
//@RequestMapping("/api/v1/weather")
//@Validated
//@Tag(
//        name = "Weather API",
//        description = "REST endpoints for retrieving real-time weather information. " +
//                "All responses are cached for 12 hours to optimize performance and reduce API calls."
//)
//public class WeatherController {
//
//    private final WeatherService weatherService;
//
//    public WeatherController(WeatherService weatherService) {
//        this.weatherService = weatherService;
//    }
//
//    @Operation(
//            summary = "Get current weather by city",
//            description = "Returns current weather data for a specified city name"
//    )
//    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Weather data retrieved successfully")
//    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "City not found")
//    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Weather service unavailable")
//    @GetMapping
//    public ApiResponse<WeatherMapper> getWeather(@Parameter(description = "City name (e.g., London, New York)", example = "London")
//                                                 @NotBlank @RequestParam String city) {
//        WeatherMapper weather = weatherService.getCurrentWeather(city);
//        return ApiResponse.success(weather, "Successfully fetched weather data");
//    }
//}

package com.pm.weatherapi.controller;

import com.pm.weatherapi.dto.ApiResponse;
import com.pm.weatherapi.dto.WeatherMapper;
import com.pm.weatherapi.service.WeatherService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/weather")
@Validated
@Tag(name = "Weather", description = "Get weather information for cities")
public class WeatherController {

    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @Operation(
            summary = "Get current weather by city",
            description = "Returns current weather data for a specified city name"
    )
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "Weather data retrieved successfully",
                    content = @Content(
                            examples = @ExampleObject(
                                    value = """
                                            {
                                              "success": true,
                                              "data": {
                                                "cityName": "London",
                                                "temperature": 15.5,
                                                "description": "clear sky",
                                                "humidity": 72,
                                                "windSpeed": 3.2
                                              },
                                              "message": "Successfully fetched weather data",
                                              "error": null
                                            }
                                            """
                            )
                    )
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "400",
                    description = "Bad request - missing or invalid city parameter",
                    content = @Content(
                            examples = @ExampleObject(
                                    value = """
                                            {
                                              "success": false,
                                              "data": null,
                                              "message": null,
                                              "error": "City must not be blank"
                                            }
                                            """
                            )
                    )
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "404",
                    description = "City not found",
                    content = @Content(
                            examples = @ExampleObject(
                                    value = """
                                            {
                                              "success": false,
                                              "data": null,
                                              "message": null,
                                              "error": "City 'InvalidCity' not found"
                                            }
                                            """
                            )
                    )
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "500",
                    description = "Weather service unavailable",
                    content = @Content(
                            examples = @ExampleObject(
                                    value = """
                                            {
                                              "success": false,
                                              "data": null,
                                              "message": null,
                                              "error": "Weather service is temporarily unavailable"
                                            }
                                            """
                            )
                    )
            )
    })
    @GetMapping
    public ApiResponse<WeatherMapper> getWeather(
            @Parameter(description = "City name (e.g., London, New York)", example = "London")
            @NotBlank @RequestParam String city) {

        WeatherMapper weather = weatherService.getCurrentWeather(city);
        return ApiResponse.success(weather, "Successfully fetched weather data");
    }
}
