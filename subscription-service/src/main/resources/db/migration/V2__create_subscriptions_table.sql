CREATE TABLE subscriptions
(
    id BIGSERIAL PRIMARY KEY,

    subscription_uuid UUID NOT NULL UNIQUE,

    customer_uuid UUID NOT NULL,

    plan_uuid UUID NOT NULL,

    status VARCHAR(20) NOT NULL,

    start_date DATE NOT NULL,

    end_date DATE NOT NULL,

    next_billing_date DATE NOT NULL,

    auto_renew BOOLEAN NOT NULL,

    created_at TIMESTAMP NOT NULL,

    updated_at TIMESTAMP NOT NULL,

    version BIGINT DEFAULT 0
);

CREATE INDEX idx_subscription_uuid
    ON subscriptions(subscription_uuid);

CREATE INDEX idx_customer_uuid
    ON subscriptions(customer_uuid);

CREATE INDEX idx_subscription_plan_uuid
    ON subscriptions(plan_uuid);

CREATE INDEX idx_subscription_status
    ON subscriptions(status)