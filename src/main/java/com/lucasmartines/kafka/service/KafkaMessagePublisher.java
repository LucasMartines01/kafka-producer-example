package com.lucasmartines.kafka.service;

import com.lucasmartines.kafka.dto.Customer;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class KafkaMessagePublisher {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public KafkaMessagePublisher(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String message) {
        CompletableFuture<SendResult<String, Object>> future = kafkaTemplate.send("topic-demo2", message);
        future.whenComplete((result, ex) -> {
            if (ex != null) {
                System.out.println("Error publishing message: " + ex.getMessage());
            } else {
                System.out.println("Message published successfully: " + result.getRecordMetadata());
            }
        });
    }

    public void sendEventsToTopic(Customer customer) {
        try {
            CompletableFuture<SendResult<String, Object>> future = kafkaTemplate.send("topic-demo", customer);
            future.whenComplete((result, ex) -> {
                if (ex != null) {
                    System.out.println("Error publishing message: " + ex.getMessage());
                } else {
                    System.out.println("Message published successfully: " + result.getRecordMetadata());
                }
            });
        }catch (Exception e){
            System.out.println("Error sending message: " + e.getMessage());
        }

    }

}
