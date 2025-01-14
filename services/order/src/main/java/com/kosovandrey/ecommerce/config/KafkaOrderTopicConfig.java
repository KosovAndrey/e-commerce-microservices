package com.kosovandrey.ecommerce.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

/**
 * Конфигурация для создания Kafka-топика, связанного с заказами.
 */
@Configuration
public class KafkaOrderTopicConfig {

    /**
     * Создает Kafka-топик для заказов.
     *
     * @return Новый топик с именем "order-topic".
     */
    @Bean
    public NewTopic orderTopic() {
        return TopicBuilder
                .name("order-topic")
                .build();
    }
}