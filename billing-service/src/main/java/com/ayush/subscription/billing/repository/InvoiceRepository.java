package com.ayush.subscription.billing.repository;

import com.ayush.subscription.billing.entity.Invoice;
import com.ayush.subscription.billing.enums.InvoiceStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface InvoiceRepository extends JpaRepository<Invoice , Long> {
    Optional<Invoice> findByInvoiceUuid(UUID invoiceUuid);

    Optional<Invoice> findByInvoiceNumber(String invoiceNumber);

    List<Invoice> findByStatus(InvoiceStatus status);

    boolean existsByInvoiceNumber(String invoiceNumber);
}
