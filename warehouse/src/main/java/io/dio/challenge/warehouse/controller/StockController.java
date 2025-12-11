package io.dio.challenge.warehouse.controller;

import io.dio.challenge.warehouse.model.StockResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/warehouse")
public class StockController {

    // synchronous endpoint used by storefront to check stock
    @GetMapping("/stock/{sku}")
    public ResponseEntity<StockResponse> getStock(@PathVariable String sku) {
        // For demo purposes return a static response. Replace with DB lookup.
        int available = Math.abs(sku.hashCode()) % 20 + 10; // pseudo-random
        return ResponseEntity.ok(new StockResponse(sku, Math.max(0, available)));
    }
}
