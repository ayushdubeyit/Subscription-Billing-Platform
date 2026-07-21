package com.ayush.subscription.payment.entity;


import com.ayush.subscription.payment.enums.PaymentMethod;
import com.ayush.subscription.payment.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.cache.annotation.EnableCaching;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(
        name = "payments" ,
        indexes = {
                @Index(name = "idx_payment_uuid" , columnList = "payment_uuid"),
                @Index(name = "idx_customer_uuid" , columnList = "customer_uuid"),
                @Index(name = "idx_subscription_uuid" , columnList = "subscription_uuid"),
                @Index(name = "idx_payment_status" , columnList = "payment_status")
        }

)
public class Payment {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "payment_uuid" , nullable = false , unique = true)
    private UUID paymentUuid;

    @Column(name = "subscription_uuid" , nullable = false , unique = true)
    private UUID subscriptionUuid;

    @Column(name = "customer_uuid" , nullable = false , unique = true)
    private UUID customerUuid;

    @Column(nullable = false , precision = 10 , scale = 2)
    private BigDecimal amount;

    private String currency;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_method" , nullable = false)
    private PaymentMethod paymentMethod;


    @Enumerated(EnumType.STRING)
    @Column(nullable = false )
    private PaymentStatus status;

    @Column(name = "transaction_id", unique = true)
    private String transactionId;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Version
    private Long version;

    @PrePersist
    public void prePersist(){
        this.paymentUuid = UUID.randomUUID();
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate(){
        this.updatedAt = LocalDateTime.now();

    }

}
