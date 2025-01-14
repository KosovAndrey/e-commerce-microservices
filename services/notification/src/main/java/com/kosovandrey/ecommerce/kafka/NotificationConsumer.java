package com.kosovandrey.ecommerce.kafka;

import com.kosovandrey.ecommerce.email.EmailService;
import com.kosovandrey.ecommerce.kafka.order.OrderConfirmation;
import com.kosovandrey.ecommerce.kafka.payment.PaymentConfirmation;
import com.kosovandrey.ecommerce.notification.Notification;
import com.kosovandrey.ecommerce.notification.NotificationRepository;
import com.kosovandrey.ecommerce.notification.NotificationType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * Сервис для обработки уведомлений из Kafka.
 * Потребляет сообщения из топиков Kafka, сохраняет уведомления в базу данных
 * и отправляет соответствующие email-уведомления.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationConsumer {

    private final NotificationRepository repository;
    private final EmailService emailService;

    /**
     * Обрабатывает сообщение из топика "payment-topic".
     * Сохраняет уведомление о подтверждении платежа в базу данных
     * и отправляет email-уведомление об успешном платеже.
     *
     * @param paymentConfirmation Данные о подтверждении платежа.
     */
    @KafkaListener(topics = "payment-topic")
    public void consumePaymentSuccessNotification(PaymentConfirmation paymentConfirmation) {
        log.info("Consuming the message from payment-topic Topic: {}", paymentConfirmation);
        try {
            // Сохранение уведомления в базу данных
            repository.save(Notification.builder()
                    .notificationType(NotificationType.PAYMENT_CONFIRMATION)
                    .notificationDate(LocalDateTime.now())
                    .paymentConfirmation(paymentConfirmation)
                    .build()
            );

            // Формирование имени клиента
            var customerName = paymentConfirmation.customerFirstname() + " " + paymentConfirmation.customerLastname();

            // Отправка email-уведомления об успешном платеже
            emailService.sendPaymentSuccessEmail(
                    paymentConfirmation.customerEmail(),
                    customerName,
                    paymentConfirmation.amount(),
                    paymentConfirmation.orderReference()
            );
        } catch (Exception e) {
            log.error("Failed to process payment confirmation: {}", paymentConfirmation, e);
        }
    }

    /**
     * Обрабатывает сообщение из топика "order-topic".
     * Сохраняет уведомление о подтверждении заказа в базу данных
     * и отправляет email-уведомление о подтверждении заказа.
     *
     * @param orderConfirmation Данные о подтверждении заказа.
     */
    @KafkaListener(topics = "order-topic")
    public void consumeOrderConfirmationNotification(OrderConfirmation orderConfirmation) {
        log.info("Consuming the message from order-topic Topic: {}", orderConfirmation);
        try {
            // Сохранение уведомления в базу данных
            repository.save(Notification.builder()
                    .notificationType(NotificationType.ORDER_CONFIRMATION)
                    .notificationDate(LocalDateTime.now())
                    .orderConfirmation(orderConfirmation)
                    .build()
            );

            // Формирование имени клиента
            var customerName = orderConfirmation.customer().firstname() + " " + orderConfirmation.customer().lastname();

            // Отправка email-уведомления о подтверждении заказа
            emailService.sendOrderConfirmationEmail(
                    orderConfirmation.customer().email(),
                    customerName,
                    orderConfirmation.amount(),
                    orderConfirmation.orderReference(),
                    orderConfirmation.products()
            );
        } catch (Exception e) {
            log.error("Failed to process order confirmation: {}", orderConfirmation, e);
        }
    }
}