package com.ayush.subscription.billing.service.interfaces;

import com.ayush.subscription.billing.dto.request.CreateBillingRequest;
import com.ayush.subscription.billing.dto.response.BillingResponse;

import java.util.UUID;

public interface BillingService {
    BillingResponse createBilling(CreateBillingRequest request);

    BillingResponse getBillingByUuid(UUID billingUuid);
}
