package com.pm.weatherapi.exceptions;

public class CityNotFoundException extends RuntimeException{
    public CityNotFoundException(String message){
        super(message);
    }
}
