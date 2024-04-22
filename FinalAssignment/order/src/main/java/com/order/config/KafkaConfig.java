package com.order.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

import static com.order.utils.Constants.TOPIC_ORDER;

@Configuration
public class KafkaConfig {
    @Bean
    public NewTopic topic() {
        return TopicBuilder
                .name(TOPIC_ORDER)
                .partitions(2)
                .replicas(2)
                .build();
    }
}