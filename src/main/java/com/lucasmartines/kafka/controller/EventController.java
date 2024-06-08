package com.lucasmartines.kafka.controller;

import com.lucasmartines.kafka.service.KafkaMessagePublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/producer")
public class EventController {
    private final KafkaMessagePublisher kafkaMessagePublisher;

    public EventController(KafkaMessagePublisher kafkaMessagePublisher) {
        this.kafkaMessagePublisher = kafkaMessagePublisher;
    }

    @PostMapping(value = "/send")
    public ResponseEntity<String> publish(@RequestBody String message) {
        try {
            kafkaMessagePublisher.sendMessage(message);
            return ResponseEntity.ok().body("Message sent successfully");
        }
        catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error sending message: " + e.getMessage());
        }
    }
}
