package com.example.Omafourm.middleware.Kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.producer.KafkaProducer;

/**
 * @param: KafkaProducer
 * @package: com.example.Omafourm.middleware.Kafka
 * @className: KafkaProducer
 * @description: 註冊MQ Producer
 */
public class KafkaSignupProducer {
    private KafkaProducer<String,String> producer;
    private ObjectMapper objectMapper = new ObjectMapper();


}
