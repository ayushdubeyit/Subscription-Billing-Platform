package com.ayush.subscription.billing.util;

import com.ayush.subscription.billing.dto.request.CreateBillingRequest;
import com.ayush.subscription.billing.dto.response.BillingResponse;
import com.ayush.subscription.billing.entity.Billing;
import com.ayush.subscription.billing.enums.BillingStatus;

import java.math.BigDecimal;
import java.util.UUID;

public final class BillingHelper {
    private BillingHelper() {
    }


    public static Billing buildBilling(CreateBillingRequest request){
        BigDecimal totalAmount = request.baseAmount()
                .subtract(request.discount())
                .add(request.tax());


        return Billing.builder()
                .billingUuid(UUID.randomUUID())
                .customerUuid(request.customerUuid())
                .subscriptionUuid(request.subscriptionUuid())
                .baseAmount(request.baseAmount())
                .discount(request.discount())
                .tax(request.tax())
                .totalAmount(totalAmount)
                .currency(request.currency())
                .status(BillingStatus.GENERATED)
                .build();

    }

    public static BillingResponse toResponse(Billing billing) {
        return new BillingResponse(
                billing.getBillingUuid(),
                billing.getCustomerUuid(),
                billing.getSubscriptionUuid(),
                billing.getTotalAmount(),
                billing.getCurrency(),
                billing.getStatus(),
                billing.getCreatedAt()
        );

    }
    }



