# Deployment guide (repository reality)

Run infrastructure with `docker compose up`; it exposes PostgreSQL 5432, Redis 6379, Kafka 9092 and Kafka UI 8090. PostgreSQL init creates the four databases. Kafka is single-node KRaft and advertises `localhost:9092`; it is therefore not container-to-container ready as configured (`docker-compose.yml`).

Start discovery separately on 8761, then the services independently, then gateway. However a complete runnable startup command is **Not found in repository**: root reactor directory/module names mismatch case, `common-lib` has no event/topic sources, and subscription-service has no application configuration. Required local endpoints from source are customer 8081, payment HTTP 8083/gRPC 9090, billing 8084, notification 8085, gateway 8080, discovery 8761. No Dockerfiles, service Compose definitions, deployment manifests, environment profiles, secrets management, readiness orchestration, or production configuration is found.
