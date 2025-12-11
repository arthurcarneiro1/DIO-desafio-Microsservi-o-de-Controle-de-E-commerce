# Microservices Spring Boot Challenge - Starter Project

This repository contains a starter template for the DIO microservices challenge with two simple services:

- **warehouse** (port 8081): exposes a synchronous HTTP endpoint to query stock.
- **storefront** (port 8080): calls warehouse synchronously and sends asynchronous messages to RabbitMQ on checkout.

## How to run (locally with Docker)

1. Build and run:
   ```
   docker-compose up --build
   ```

2. Check RabbitMQ management UI at: http://localhost:15672 (guest/guest)

3. Example requests:
   - Check stock: `GET http://localhost:8080/api/storefront/stock/ABC123`
   - Checkout: `POST http://localhost:8080/api/storefront/checkout` with JSON body:
     ```json
     { "sku": "ABC123", "quantity": 2 }
     ```

## What to improve / next steps (suggestions)
- Replace static/pseudo-random stock with a real DB (Postgres) and Spring Data repositories.
- Add DTOs, validation and exception handling.
- Implement consumers in warehouse to process `stock.updated` messages and decrement stock.
- Add tests (unit/integration) and CI (GitHub Actions).
- Add proper logging and observability (Zipkin / Sleuth, Prometheus exporters).

## Deliverable
This is a minimal starter to help you iterate quickly. Fork, improve and document your decisions in the repository README and submit the repo link in DIO.
