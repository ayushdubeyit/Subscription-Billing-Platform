CREATE TABLE subscription_plans
(
    id BIGSERIAL PRIMARY KEY,

    plan_uuid UUID NOT NULL UNIQUE,

    name VARCHAR(100) NOT NULL,

    description VARCHAR(500),

    price NUMERIC(10,2) NOT NULL,

    billing_cycle VARCHAR(20) NOT NULL,

    status VARCHAR(20) NOT NULL,

    created_at TIMESTAMP NOT NULL,

    updated_at TIMESTAMP NOT NULL,

    version BIGINT DEFAULT 0
);

CREATE INDEX idx_plan_uuid
ON subscription_plans(plan_uuid);

CREATE INDEX idx_plan_status
ON subscription_plans(status);