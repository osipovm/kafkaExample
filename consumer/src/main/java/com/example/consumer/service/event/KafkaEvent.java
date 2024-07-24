package com.example.consumer.service.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KafkaEvent {
    private String productId;
    private String title;
    private BigDecimal price;
    private Integer quantity;
}
