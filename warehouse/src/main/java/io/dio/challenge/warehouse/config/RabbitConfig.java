package io.dio.challenge.warehouse.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
    public static final String STOCK_UPDATED_QUEUE = "stock.updated";

    @Bean
    public Queue stockUpdatedQueue() {
        return new Queue(STOCK_UPDATED_QUEUE, false);
    }
}
