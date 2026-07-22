package com.ayush.subscription.billing.entity;

import com.ayush.subscription.billing.enums.BillingStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "billing")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Billing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "billing_uuid", nullable = false, unique = true)
    private UUID billingUuid;

    @Column(name = "customer_uuid", nullable = false)
    private UUID customerUuid;

    @Column(name = "subscription_uuid", nullable = false)
    private UUID subscriptionUuid;

    @Column(name = "base_amount", nullable = false, precision = 10, scale = 2)
    private BigDecimal baseAmount;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal discount;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal tax;

    @Column(name = "total_amount", nullable = false, precision = 10, scale = 2)
    private BigDecimal totalAmount;

    @Column(nullable = false)
    private String currency;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BillingStatus status;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @Version
    private Long version;

    @OneToOne(mappedBy = "billing",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            orphanRemoval = true)
    private Invoice invoice;


}
