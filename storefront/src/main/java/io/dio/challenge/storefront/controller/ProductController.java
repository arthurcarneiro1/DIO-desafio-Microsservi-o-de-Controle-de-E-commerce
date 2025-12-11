package io.dio.challenge.storefront.controller;

import io.dio.challenge.storefront.model.CheckoutRequest;
import io.dio.challenge.storefront.model.StockResponse;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;

@RestController
@RequestMapping("/api/storefront")
public class ProductController {

    private final WebClient webClient;
    private final RabbitTemplate rabbitTemplate;
    @Value("${warehouse.url:http://localhost:8081}")
    private String warehouseBase;

    public ProductController(RabbitTemplate rabbitTemplate) {
        this.webClient = WebClient.create();
        this.rabbitTemplate = rabbitTemplate;
    }

    @GetMapping("/stock/{sku}")
    public ResponseEntity<StockResponse> checkStock(@PathVariable String sku) {
        // synchronous HTTP call to warehouse
        StockResponse resp = webClient.get()
                .uri(warehouseBase + "/api/warehouse/stock/" + sku)
                .retrieve()
                .bodyToMono(StockResponse.class)
                .block();
        return ResponseEntity.ok(resp);
    }

    @PostMapping("/checkout")
    public ResponseEntity<String> checkout(@RequestBody CheckoutRequest req) {
        // In a real system we'd validate and persist an order. Here we just send a message to RabbitMQ.
        rabbitTemplate.convertAndSend("stock.updated", req.getSku() + ":-" + req.getQuantity());
        return ResponseEntity.ok("Checkout request queued");
    }
}
