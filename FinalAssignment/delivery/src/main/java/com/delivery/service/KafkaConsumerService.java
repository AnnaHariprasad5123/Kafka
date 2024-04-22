package com.delivery.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import static com.delivery.utils.Constants.TOPIC_LOCATION;
import static com.delivery.utils.Constants.TOPIC_ORDER;

@Slf4j
@Service
public class KafkaConsumerService {

    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public KafkaConsumerService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @KafkaListener(topics = TOPIC_ORDER)
    public void consumeOrderUpdates(String message) {
        log.info("Consumed message from order topic: " + message);
        sendLocation(message);
    }

    public void sendLocation(String id) {
        for (int i = 1; i <= 10; i++) {
            kafkaTemplate.send(TOPIC_LOCATION, "( " + Math.round(Math.random() * 100) + " , " + Math.round(Math.random() * 100) + " " + ")");
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                log.error(e.getMessage());
                Thread.currentThread().interrupt();
            }
        }
        kafkaTemplate.send(TOPIC_LOCATION, id);
        kafkaTemplate.send(TOPIC_LOCATION, "done");
    }
}
