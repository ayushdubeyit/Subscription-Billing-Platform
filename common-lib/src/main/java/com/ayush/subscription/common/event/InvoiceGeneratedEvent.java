package com.ayush.subscription.common.event;

import com.ayush.subscription.common.enums.EventType;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.UUID;
@Getter
public class InvoiceGeneratedEvent extends BaseEvent{


    private final UUID invoiceUuid;
    private final UUID billingUuid;
    private final UUID customerUuid;
    private final BigDecimal totalAmount;
    private final String currency;



    public InvoiceGeneratedEvent(
            UUID invoiceUuid,
            UUID billingUuid,
            UUID customerUuid,
            BigDecimal totalAmount,
            String currency) {

        super(EventType.INVOICE_GENERATED);

        this.invoiceUuid = invoiceUuid;
        this.billingUuid = billingUuid;
        this.customerUuid = customerUuid;
        this.totalAmount = totalAmount;
        this.currency = currency;
    }
}
