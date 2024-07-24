package com.example.consumer.handler;


import com.example.consumer.service.event.KafkaEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class ProductHandler {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @KafkaListener(topics = "my-first-topic", groupId = "kafka-events")
    public void handle(String kafkaEvent){
        LOGGER.info("Received event: {}", kafkaEvent);
    }
}
