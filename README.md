
---

# **Desafio de Microsserviços Spring Boot – Projeto Inicial**

Este repositório contém um template inicial para o desafio de microsserviços da DIO com dois serviços simples:

* **warehouse** (porta 8081): expõe um endpoint HTTP síncrono para consultar estoque.
* **storefront** (porta 8080): chama o warehouse de forma síncrona e envia mensagens assíncronas para o RabbitMQ no checkout.

## **Como executar (localmente com Docker)**

1. Build e execução:

   ```
   docker-compose up --build
   ```

2. Acesse a interface de gerenciamento do RabbitMQ em:
   [http://localhost:15672](http://localhost:15672) (usuário/senha: guest/guest)

3. Exemplos de requisições:

   * Consultar estoque:
     `GET http://localhost:8080/api/storefront/stock/ABC123`
   * Checkout:
     `POST http://localhost:8080/api/storefront/checkout`
     Corpo JSON:

     ```json
     { "sku": "ABC123", "quantity": 2 }
     ```

## **O que melhorar / próximos passos (sugestões)**

* Substituir o estoque estático/pseudo-aleatório por um banco de dados real (Postgres) usando Spring Data.
* Adicionar DTOs, validação e tratamento de exceções.
* Implementar consumidores no warehouse para processar mensagens `stock.updated` e decrementar o estoque.
* Adicionar testes (unitários e de integração) e CI (GitHub Actions).
* Adicionar logs apropriados e observabilidade (Zipkin / Sleuth, Prometheus exporters).



---


