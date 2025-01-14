package com.kosovandrey.ecommerce.notification;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

/**
 * Сервис для отправки уведомлений о платежах через Kafka.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationProducer {
    private final KafkaTemplate<String, PaymentNotificationRequest> kafkaTemplate;

    /**
     * Отправляет уведомление о платеже в Kafka.
     *
     * @param request Запрос на уведомление о платеже.
     */
    public void sendNotification(PaymentNotificationRequest request) {
        log.info("Sending notification with body <{}>", request);
        Message<PaymentNotificationRequest> message = MessageBuilder
                .withPayload(request)
                .setHeader(KafkaHeaders.TOPIC, "payment-topic")
                .build();
        kafkaTemplate.send(message);
        log.info("Notification sent successfully for order reference: {}", request.orderReference());
    }
}