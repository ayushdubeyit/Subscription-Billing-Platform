CREATE TABLE payments
(
    id BIGSERIAL PRIMARY KEY,

    payment_uuid UUID NOT NULL UNIQUE,

    subscription_uuid UUID NOT NULL,

    customer_uuid UUID NOT NULL,

    amount NUMERIC(10,2) NOT NULL,

    currency VARCHAR(10) NOT NULL,

    payment_method VARCHAR(30) NOT NULL,

    status VARCHAR(30) NOT NULL,

    transaction_id VARCHAR(255) UNIQUE,

    created_at TIMESTAMP NOT NULL,

    updated_at TIMESTAMP,

    version BIGINT DEFAULT 0
);

CREATE INDEX idx_payment_uuid
ON payments(payment_uuid);

CREATE INDEX idx_subscription_uuid
ON payments(subscription_uuid);

CREATE INDEX idx_customer_uuid
ON payments(customer_uuid);

CREATE INDEX idx_payment_status
ON payments(status);