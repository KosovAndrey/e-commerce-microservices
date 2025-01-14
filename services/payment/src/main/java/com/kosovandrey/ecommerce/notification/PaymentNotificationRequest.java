package com.kosovandrey.ecommerce.notification;

import com.kosovandrey.ecommerce.payment.PaymentMethod;

import java.math.BigDecimal;

/**
 * Запрос на уведомление о платеже.
 *
 * @param orderReference    Уникальный идентификатор заказа.
 * @param amount            Сумма платежа.
 * @param paymentMethod     Метод оплаты.
 * @param customerFirstName Имя клиента.
 * @param customerLastName  Фамилия клиента.
 * @param customerEmail     Электронная почта клиента.
 */
public record PaymentNotificationRequest(
        String orderReference,
        BigDecimal amount,
        PaymentMethod paymentMethod,
        String customerFirstName,
        String customerLastName,
        String customerEmail
) {
}