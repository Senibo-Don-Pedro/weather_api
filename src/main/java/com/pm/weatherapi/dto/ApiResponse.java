package com.pm.weatherapi.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Standard API response wrapper that ensures all our endpoints
 * return data in a consistent format. This makes it easier for
 * frontend developers to handle responses predictably.
 */
@JsonInclude(JsonInclude.Include.NON_NULL) // Only include non-null fields in JSON
public record ApiResponse<T>(
        boolean success,
        T data,
        String message,
        String error
) {

    public static <T> ApiResponse<T> success(T data, String message) {
        return new ApiResponse<>(true, data, message, null );
    }

    public static <T> ApiResponse<T> error (String error){
        return new ApiResponse<>(false,null,null, error);
    }
}
