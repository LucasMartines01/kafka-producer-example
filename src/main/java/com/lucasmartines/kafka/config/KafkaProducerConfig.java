package com.lucasmartines.kafka.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaProducerConfig {
    @Bean
    public NewTopic createTopic() {
        return new NewTopic("topic-demo", 5, (short) 1);
    }
}
