package com.example.producer1.controller;

import com.example.producer1.config.ErrorMessage;
import com.example.producer1.dto.ProductDto;
import com.example.producer1.service.ProductDtoService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductDtoService productDtoService;
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    //асинхронный
    @PostMapping("/as")
    public ResponseEntity addProductAs(@RequestBody ProductDto productDto) {
        String productId = productDtoService.createAsinh(productDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(productId);
    }

    //синхронный
    @PostMapping("/s1")
    public ResponseEntity<Object> addProductS1(@RequestBody ProductDto productDto) {
        String productId = productDtoService.createSinh1(productDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(productId);
    }
    //синхронный
    @PostMapping("/s2")
    public ResponseEntity<Object> addProductS2(@RequestBody ProductDto productDto) {
        String productId = null;
        try {
            productId = productDtoService.createSinh2(productDto);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new ErrorMessage(new Date(), e.getMessage()));
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(productId);
    }


}