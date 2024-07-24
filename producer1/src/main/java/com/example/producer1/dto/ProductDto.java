package com.example.producer1.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class ProductDto {
    private String title;
    private BigDecimal price;
    private Integer quantity;
}
