# Manual testing plan

Precondition: resolve known build/configuration blockers documented in `KNOWN_ISSUES.md`; do not treat this document as evidence they are fixed.

1. Infrastructure: start Compose; verify Postgres/Redis/Kafka/Kafka UI health. Launch discovery and each service; check `/actuator/health` where Actuator is configured.
2. Customer direct GraphQL: register (expect null access token), login (JWT), create/get/list/update/delete customer; repeat duplicate email, bad phone/email, missing fields, deleted customer, bad/expired/tampered JWT.
3. Gateway: obtain JWT directly from customer service, then exercise customer/plan/subscription/billing queries/mutations through gateway; verify header forwarding; issue 21 GraphQL requests from one IP within a minute and expect 429.
4. Subscription: create plan, duplicate plan, update/delete it; create subscription with valid IDs and payment service running; verify payment, subscription, billing and invoice rows plus three emitted notifications. Exercise unavailable customer/payment/billing and inspect partial writes.
5. Payment: direct GraphQL create/get/list/update/delete; gRPC `ProcessPayment` and `GetPaymentHistory`; test malformed UUID, nonpositive amount, duplicate subscription/customer, terminal status update. `getPaymentsBySubscription` should be recorded as expected schema/resolver defect.
6. Billing: create/get/list billing/invoice; test missing UUID, negative discount/tax/base values, invoice uniqueness; consume invoice topic.
7. Kafka/recovery: stop Notification then create events and restart; observe earliest/group offset semantics. Test poison payload and producer broker outage. Retry/DLT behaviour is expected absent by source.
8. Security/performance: unauthenticated gateway requests, role/ownership access attempts, direct downstream access, rate-limit memory growth with many IPs, concurrent customer/plan updates (optimistic lock), Kafka/Redis/Postgres outage, and load/soak tests. SLOs and automated test coverage beyond empty application-context tests are **Not found in repository**.
