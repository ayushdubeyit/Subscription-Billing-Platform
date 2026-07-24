# Known issues and source evidence

1. **Build blockers:** `common-lib` contains no Java sources although imported `KafkaTopics`/event classes are required (`common-lib/pom.xml`; producer/consumer imports). Root POM module names do not match actual case-sensitive directory names, and excludes discovery (`pom.xml`).
2. **Subscription cannot be configured from repository:** no properties/YAML despite required DB/Redis/Kafka/Eureka/billing/customer properties (`SubscriptionServiceImpl`; `GraphqlClientConfig`; `rg --files`).
3. **No service containers/start ordering:** Compose supplies infrastructure only (`docker-compose.yml`).
4. **Security gap:** customer permits all requests; its JWT filters are not attached; payment/billing/subscription unprotected (`customer/.../SecurityConfig.java`; security config absence). Gateway lacks login route.
5. **GraphQL mismatch:** payment schema declares `getPaymentsBySubscription`, but `PaymentResolver` does not (`payment-service` schema/resolver).
6. **Distributed inconsistency:** subscription writes and payment occur before synchronous billing call; no compensation/outbox and Kafka failure only logs (`SubscriptionServiceImpl.createSubscription`; producers).
7. **Payment is simulated:** helper forces SUCCESS/CARD; no processor or idempotency (`PaymentHelper.toEntity`).
8. **No renewal/retry/notification delivery:** no scheduled task, retry workflow, email/SMS client, DLT, or listener retry found.
9. **Redis unsafe typing / credentials:** unsafe default typing and localhost unauthenticated Redis config (`RedisConfig`; customer properties).
10. **Schema/model hazards:** Payment entity has unique annotations absent from migration; subscription V2 final statement lacks semicolon; migration validation can fail (`Payment.java`; V2 SQL).
11. **Observability/readiness:** basic Actuator exposure exists in several services, but no tracing/circuit breaker/central logs or app health dependencies; notification has no Actuator dependency/config.
12. **Production readiness audit:** input validation is uneven (auth/billing use `@Valid`, plan/subscription/payment resolver paths do not); global exception mapping exists only customer/billing; no idempotency keys, rate limit persistence, bulkheads, fallbacks, backup/restore plan, performance tests, or availability design is found. Relevant evidence: resolver classes under each module's `graphql` package, `RateLimitingFilter`, and absent matching configuration/source via repository inventory.
