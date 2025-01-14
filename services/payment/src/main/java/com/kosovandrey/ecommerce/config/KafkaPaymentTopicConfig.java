package com.kosovandrey.ecommerce.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

/**
 * Конфигурация Kafka для создания топика, связанного с платежами.
 */
@Slf4j
@Configuration
public class KafkaPaymentTopicConfig {

    /**
     * Создает новый топик для платежей.
     *
     * @return Новый топик с именем "payment-topic".
     */
    @Bean
    public NewTopic paymentTopic() {
        log.info("Creating Kafka topic: payment-topic");
        return TopicBuilder.name("payment-topic").build();
    }
}