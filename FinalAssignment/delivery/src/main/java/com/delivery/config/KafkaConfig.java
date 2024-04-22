package com.delivery.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

import static com.delivery.utils.Constants.TOPIC_LOCATION;

@Configuration
public class KafkaConfig {
    @Bean
    public NewTopic topic() {
        return TopicBuilder
                .name(TOPIC_LOCATION)
                .partitions(2)
                .replicas(3)
                .build();
    }
}