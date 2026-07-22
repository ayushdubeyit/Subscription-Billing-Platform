package com.ayush.subscription.billing.graphql;


import com.ayush.subscription.billing.dto.response.InvoiceResponse;
import com.ayush.subscription.billing.service.interfaces.InvoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class InvoiceQuery {

    private final InvoiceService invoiceService;

    @QueryMapping
    public InvoiceResponse getInvoice(
            @Argument UUID invoiceUuid
    ) {
        return invoiceService.getInvoice(invoiceUuid);
    }
}
