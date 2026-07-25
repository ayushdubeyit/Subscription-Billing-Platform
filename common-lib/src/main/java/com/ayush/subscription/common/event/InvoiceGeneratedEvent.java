package com.ayush.subscription.common.event;

import com.ayush.subscription.common.enums.EventType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
public class InvoiceGeneratedEvent extends BaseEvent{

    private UUID invoiceUuid;
    private UUID billingUuid;
    private UUID customerUuid;
    private BigDecimal totalAmount;
    private String currency;

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
