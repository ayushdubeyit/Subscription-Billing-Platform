CREATE TABLE billing
(
    id BIGSERIAL PRIMARY KEY,

    billing_uuid UUID NOT NULL UNIQUE,

    customer_uuid UUID NOT NULL,

    subscription_uuid UUID NOT NULL,

    base_amount NUMERIC(10,2) NOT NULL,

    discount NUMERIC(10,2) DEFAULT 0,

    tax NUMERIC(10,2) DEFAULT 0,

    total_amount NUMERIC(10,2) NOT NULL,

    currency VARCHAR(10) NOT NULL,

    status VARCHAR(20) NOT NULL,

    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    version BIGINT NOT NULL DEFAULT 0
);

CREATE TABLE invoice
(
    id BIGSERIAL PRIMARY KEY,

    invoice_uuid UUID NOT NULL UNIQUE,

    invoice_number VARCHAR(100) NOT NULL UNIQUE,

    billing_id BIGINT NOT NULL UNIQUE,

    amount NUMERIC(10,2) NOT NULL,

    status VARCHAR(20) NOT NULL,

    issued_at TIMESTAMP NOT NULL,

    due_date TIMESTAMP,

    paid_at TIMESTAMP,

    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    version BIGINT DEFAULT 0,

    CONSTRAINT fk_invoice_billing
        FOREIGN KEY (billing_id)
        REFERENCES billing(id)
);

CREATE INDEX idx_billing_customer_uuid
ON billing(customer_uuid);

CREATE INDEX idx_billing_subscription_uuid
ON billing(subscription_uuid);

CREATE INDEX idx_invoice_number
ON invoice(invoice_number);