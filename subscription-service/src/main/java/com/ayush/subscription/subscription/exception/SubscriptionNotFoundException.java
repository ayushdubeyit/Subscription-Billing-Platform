package com.ayush.subscription.subscription.exception;

import java.util.UUID;

public class SubscriptionNotFoundException extends RuntimeException{
    public SubscriptionNotFoundException(UUID uuid) {
        super("Subscription not found : " + uuid);
    }
}
