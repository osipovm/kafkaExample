package com.example.producer1.service;
import com.example.producer1.config.KafkaConfig;
import com.example.producer1.dto.ProductDto;
import com.example.producer1.service.event.KafkaEvent;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;


@Service
@AllArgsConstructor
public class ProductDtoService {
    private KafkaTemplate<String, KafkaEvent> kafkaTemplate;
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());




    //Асинхронный,  мы отправили, не дождались ответа и продолжили дальше!!!
    public String createAsinh(ProductDto productDto) {
        //TODO save db
        String productId = UUID.randomUUID().toString();
        KafkaEvent kafkaEvent = new KafkaEvent(productId, productDto.getTitle(), productDto.getPrice(),
                productDto.getQuantity());

        CompletableFuture<SendResult<String,KafkaEvent>> future= kafkaTemplate.send(
                "my-first-topic", productId, kafkaEvent);

        future.whenComplete((result, exception) -> {
            if (exception != null){
                LOGGER.error(("Ошибка при отправкке сообщения: {}" + exception.getMessage()));
            } else {
                LOGGER.info("Сообщение доставлено : {}", result.getRecordMetadata());

            }
        });
        LOGGER.info("Return : {}", productId);

        return productId;
    }



    public String createSinh1(ProductDto productDto) {
        //TODO save db1
        String productId = UUID.randomUUID().toString();
        KafkaEvent kafkaEvent = new KafkaEvent(productId, productDto.getTitle(), productDto.getPrice(),
                productDto.getQuantity());

        CompletableFuture<SendResult<String,KafkaEvent>> future= kafkaTemplate.send(
                "my-first-topic", productId, kafkaEvent);

        future.whenComplete((result, exception) -> {
            if (exception != null){
                LOGGER.error(("Ошибка при отправкке сообщения: {}" + exception.getMessage()));
            } else {
                LOGGER.info("Сообщение доставлено : {}", result.getRecordMetadata());

            }
        });
        future.join();
        LOGGER.info("Return : {}", productId);

        return productId;
    }

    public String createSinh2(ProductDto productDto) throws ExecutionException, InterruptedException {
        //TODO save db1
        String productId = UUID.randomUUID().toString();
        KafkaEvent kafkaEvent = new KafkaEvent(productId, productDto.getTitle(), productDto.getPrice(),
                productDto.getQuantity());

        SendResult<String,KafkaEvent> result= kafkaTemplate.send(
                "my-first-topic", productId, kafkaEvent).get();

        LOGGER.info("topic : {}", result.getRecordMetadata().topic());
        LOGGER.info("partition : {}", result.getRecordMetadata().partition());
        LOGGER.info("offset : {}", result.getRecordMetadata().offset());


        LOGGER.info("Return : {}", productId);

        return productId;
    }
}