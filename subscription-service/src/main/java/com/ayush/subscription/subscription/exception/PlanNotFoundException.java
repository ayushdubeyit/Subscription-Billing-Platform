package com.ayush.subscription.subscription.exception;


import java.util.UUID;

public class PlanNotFoundException extends RuntimeException{
    public PlanNotFoundException(UUID uuid) {
        super("plan not found: " + uuid);
    }
}
