package com.order.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import static com.order.utils.Constants.TOPIC_ORDER;

@Service
@Slf4j
public class KafkaService {
    private KafkaTemplate<String, String> kafkaTemplate;
    
    @Autowired
    public KafkaService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }
    
    public boolean orderDetails(String orderDetails) {
        log.info(orderDetails);
        kafkaTemplate.send(TOPIC_ORDER,orderDetails);
        return true;
    }
}