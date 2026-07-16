CREATE TABLE customers
(
    id BIGSERIAL PRIMARY KEY,

    customer_uuid UUID NOT NULL UNIQUE,

    first_name VARCHAR(100) NOT NULL,

    last_name VARCHAR(100) NOT NULL,

    email VARCHAR(255) NOT NULL UNIQUE,

    phone VARCHAR(20),

    status VARCHAR(20) NOT NULL,

    created_at TIMESTAMP NOT NULL,

    updated_at TIMESTAMP NOT NULL,

    version BIGINT NOT NULL DEFAULT 0
);

CREATE INDEX idx_customer_email
ON customers(email);

CREATE INDEX idx_customer_uuid
ON customers(customer_uuid);