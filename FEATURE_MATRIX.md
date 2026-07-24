# Feature matrix

| Feature | Status | Evidence | Confidence |
|---|---|---|---|
| Customer CRUD | Implemented, direct & gateway | `CustomerServiceImpl`; gateway customer resolvers | 95% |
| Registration/login JWT | Partially implemented | `AuthenticationServiceImpl`; registration token is null, downstream auth ineffective | 95% |
| Plan CRUD | Implemented | `SubscriptionPlanServiceImpl` | 95% |
| Subscription creation | Partially implemented | `SubscriptionServiceImpl.createSubscription`; no resilience/config | 95% |
| Subscription update/cancel | Partially implemented | update/save and physical delete only | 95% |
| Payment | Partially implemented | forced SUCCESS `PaymentHelper`; gRPC server | 95% |
| Billing/invoice | Partially implemented | `BillingServiceImpl.createBilling` | 95% |
| Notifications | Partially implemented | Kafka consumers log only | 98% |
| Kafka eventing | Partially implemented / build-blocked | producers/consumers import absent common sources | 98% |
| Redis cache | Partially implemented | two Redis configs, missing subscription runtime config | 95% |
| Gateway | Partially implemented | handwritten GraphQL clients/resolvers | 95% |
| Discovery | Implemented as standalone | Eureka app/config, not root module | 90% |
| Renewals, retries, DLT, saga/outbox | Missing | Not found in repository | 98% |
| Dockerized application deployment | Missing | Compose infrastructure only | 98% |
