package com.ayush.subscription.billing.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.atomic.AtomicLong;

public class BillingUtil {
    private static final AtomicLong COUNTER = new AtomicLong(1);
    public static String generateInvoiceNumber() {

        String date = LocalDate.now()
                .format(DateTimeFormatter.BASIC_ISO_DATE);

        long sequence = COUNTER.getAndIncrement();

        return String.format(
                "INV-%s-%06d",
                date,
                sequence
        );
    }
}
