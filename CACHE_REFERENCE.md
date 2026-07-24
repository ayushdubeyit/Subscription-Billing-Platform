# Cache reference

Customer (`RedisConfig.cacheManager`) and subscription (`RedisConfig.cacheManager`) configure Redis JSON values using `GenericJacksonJsonRedisSerializer.enableUnsafeDefaultTyping`, string keys, null suppression, and default 30-minute TTL. Redis endpoint is configured only for customer (`localhost:6379`); subscription config is **Not found in repository**.

| Cache | Get | Update | Evict | Risk |
|---|---|---|---|
| `customers` | `CustomerServiceImpl.getCustomerByUuid` `@Cacheable` | `updateCustomer` `@CachePut` | `deleteCustomer` `@CacheEvict` | Direct repository writes/auth status changes do not invalidate; cache key uses UUID. |
| `subscriptionPlans` | `SubscriptionPlanServiceImpl.getPlanByUuid` | `updatePlan` | `deletePlan` | create does not cache; no list caching. |
| `subscriptions` | `SubscriptionServiceImpl.getSubscriptionByUuid` | `updateSubscription` | `cancelSubscription` | no cache invalidation for customer/plan relationship changes. |

Unsafe polymorphic default typing is configured in both Redis configs and is a deserialization-security concern if untrusted values reach Redis. Cache metrics/eviction policy beyond TTL, Redis auth/TLS, distributed locks, and cache warmup are **Not found in repository**.
