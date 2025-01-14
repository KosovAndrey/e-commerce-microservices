package com.kosovandrey.ecommerce.notification;

import com.kosovandrey.ecommerce.kafka.order.OrderConfirmation;
import com.kosovandrey.ecommerce.kafka.payment.PaymentConfirmation;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

/**
 * Сущность уведомления.
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Document
public class Notification {
    @Id
    private String id;                                 // Уникальный идентификатор уведомления

    private NotificationType notificationType;         // Тип уведомления

    private LocalDateTime notificationDate;            // Дата и время создания уведомления

    private OrderConfirmation orderConfirmation;       // Данные подтверждения заказа

    private PaymentConfirmation paymentConfirmation;   // Данные подтверждения оплаты
}