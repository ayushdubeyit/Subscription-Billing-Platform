package com.ayush.subscription.billing.service.impl;


import com.ayush.subscription.billing.dto.request.CreateBillingRequest;
import com.ayush.subscription.billing.dto.response.BillingResponse;
import com.ayush.subscription.billing.entity.Billing;
import com.ayush.subscription.billing.entity.Invoice;
import com.ayush.subscription.billing.exception.BillingNotFoundException;
import com.ayush.subscription.billing.repository.BillingRepository;
import com.ayush.subscription.billing.repository.InvoiceRepository;
import com.ayush.subscription.billing.service.interfaces.BillingService;
import com.ayush.subscription.billing.util.BillingHelper;
import com.ayush.subscription.billing.util.InvoiceHelper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;



@Service
@RequiredArgsConstructor
@Transactional
public class BillingServiceImpl implements BillingService {

    private final BillingRepository billingRepository;
    private final InvoiceRepository invoiceRepository;

    @Override
    public BillingResponse createBilling(CreateBillingRequest request) {
        Billing billing = BillingHelper.buildBilling(request);
        Billing savedBilling = billingRepository.save(billing);


        String invoiceNumber = String.format(
                "INV-%s-%06d",
                LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE),
                savedBilling.getId()
        );


        Invoice invoice =
                InvoiceHelper.buildInvoice(
                        savedBilling,
                        invoiceNumber
                );
        invoiceRepository.save(invoice);
        return BillingHelper.toResponse(savedBilling);

    }

    @Override
    public BillingResponse getBillingByUuid(UUID billingUuid) {

        Billing billing = billingRepository
                .findByBillingUuid(billingUuid)
                .orElseThrow(() ->
                        new BillingNotFoundException(billingUuid));

        return BillingHelper.toResponse(billing);
    }

    @Override
    @Transactional
    public List<BillingResponse> getBillingsByCustomer(UUID customerUuid) {

        return billingRepository.findByCustomerUuid(customerUuid)
                .stream()
                .map(BillingHelper::toResponse)
                .toList();
    }

    @Override
    @Transactional
    public List<BillingResponse> getBillingsBySubscription(UUID subscriptionUuid) {
        return billingRepository.findBySubscriptionUuid(subscriptionUuid)
                .stream()
                .map(BillingHelper::toResponse)
                .toList();
    }
}
