package com.ayush.subscription.billing.entity;

import com.ayush.subscription.billing.enums.InvoiceStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "invoice")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "invoice_uuid",
            nullable = false,
            unique = true)
    private UUID invoiceUuid;

    @Column(name = "invoice_number",
            nullable = false,
            unique = true)
    private String invoiceNumber;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "billing_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_invoice_billing")
    )
    private Billing billing;

    @Column(nullable = false,
            precision = 10,
            scale = 2)
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private InvoiceStatus status;

    @Column(name = "issued_at")
    private LocalDateTime issuedAt;

    @Column(name = "due_date")
    private LocalDateTime dueDate;

    @Column(name = "paid_at")
    private LocalDateTime paidAt;

    @CreationTimestamp
    @Column(name = "created_at",
            updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Version
    private Long version;
}
