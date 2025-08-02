package com.pm.weatherapi.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Standard API response wrapper that ensures all our endpoints
 * return data in a consistent format. This makes it easier for
 * frontend developers to handle responses predictably.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "Standard API response wrapper")
public record ApiResponse<T>(

        @Schema(description = "Request success status", example = "true")
        boolean success,

        @Schema(description = "Response data")
        T data,

        @Schema(description = "Success message", example = "Successfully fetched weather data")
        String message,

        @Schema(description = "Error message (when success is false)", example = "City not found")
        String error
) {

    public static <T> ApiResponse<T> success(T data, String message) {
        return new ApiResponse<>(true, data, message, null);
    }

    public static <T> ApiResponse<T> error(String error) {
        return new ApiResponse<>(false, null, null, error);
    }
}
