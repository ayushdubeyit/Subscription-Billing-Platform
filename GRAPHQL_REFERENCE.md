# GraphQL reference

All schemas are `.graphqls` at the paths below and default endpoint is `/graphql` where configured. Spring resolver annotations are the source of operation binding.

| Service schema / resolver | Queries | Mutations | Authorization / validation |
|---|---|---|---|
| customer: `customer-service/src/main/resources/graphql/{customers,auth}.graphqls` | `customerByUuid`, `customers` via `CustomerQuery` | `register`, `login` via `AuthMutation`; customer CRUD via `CustomerMutation` | request `@Valid` only on auth. `@PreAuthorize` exists on CRUD/query methods but `SecurityConfig.securityFilterChain` permits every request and does not enable method security, so enforcement is not established by source. |
| subscription: `subscription-service/src/main/resources/graphql/subscription.graphqls` | plans and subscriptions via `SubscriptionPlanQuery`, `SubscriptionQuery` | plan/subscription CRUD via mutations | No auth / bean validation found. |
| payment: `payment-service/src/main/resources/graphql/payments.graphqls` | schema declares `getPayment`, customer and subscription histories; resolver implements only first two | create/update/delete | no auth; `CreatePaymentRequest` constraints are not activated by `@Valid` at resolver. **Schema field `getPaymentsBySubscription` has no resolver.** |
| billing: `billing-service/src/main/resources/graphql/billing.graphqls` | health/billing/invoice/customer/subscription via `BillingQuery`, `InvoiceQuery` | `createBilling` | mutation request has `@Valid`; no auth. |
| gateway: `GraphQL-Gateway/src/main/resources/graphql/schema.graphqls` | customer, plan, subscription, billing/invoice routes | customer, plan, subscription routes | all except GraphiQL/health require JWT at servlet layer (`GatewaySecurityConfig.securityFilterChain`). Gateway excludes auth and payment operations by schema. |

Gateway is client-side aggregation, not GraphQL schema stitching: `GraphQLClientConfig` creates three `HttpGraphQlClient`s and resolver/client pairs send handwritten downstream documents, calling `.block()` (`GraphQL-Gateway/src/main/java/com/ayush/subscription/gateway/config/GraphQLClientConfig.java`; `.../client/*.java`). Authorization is forwarded by `WebClientConfig.authorizationHeaderForwardingFilter`; gateway has no downstream error mapping, fallback or explicit timeout.

Schemas duplicate Customer/plan/subscription/billing types across gateway and downstream. This is intentional façade duplication by file presence, not a schema-stitching implementation. Resolver `CustomerQuery.customers` is downstream-only (no gateway route). `PaymentService.getPaymentsBySubscription` exists but resolver not exposed. No resolver methods not represented by their local schemas were found, except no source can prove runtime schema merging behaviour beyond Spring GraphQL conventions.
