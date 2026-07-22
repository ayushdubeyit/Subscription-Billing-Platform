package com.ayush.subscription.billing.service.impl;


import com.ayush.subscription.billing.dto.response.InvoiceResponse;
import com.ayush.subscription.billing.entity.Invoice;
import com.ayush.subscription.billing.exception.InvoiceNotFoundException;
import com.ayush.subscription.billing.repository.InvoiceRepository;
import com.ayush.subscription.billing.service.interfaces.InvoiceService;
import com.ayush.subscription.billing.util.InvoiceHelper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class InvoiceServiceImpl implements InvoiceService {


    private final InvoiceRepository invoiceRepository;

    @Override
    public InvoiceResponse getInvoice(UUID invoiceUuid) {

        Invoice invoice = invoiceRepository
                .findByInvoiceUuid(invoiceUuid)
                .orElseThrow(() ->
                        new InvoiceNotFoundException(invoiceUuid));

        return InvoiceHelper.toResponse(invoice);

    }
}
