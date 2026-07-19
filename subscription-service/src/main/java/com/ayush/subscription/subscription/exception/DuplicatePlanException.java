package com.ayush.subscription.subscription.exception;

public class DuplicatePlanException extends RuntimeException{

    public DuplicatePlanException(String planName){
        super("Plan already exists : " + planName);
    }
}
