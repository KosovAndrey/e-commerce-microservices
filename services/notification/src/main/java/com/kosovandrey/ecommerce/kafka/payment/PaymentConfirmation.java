package com.kosovandrey.ecommerce.kafka.payment;

import java.math.BigDecimal;

/**
 * Модель подтверждения оплаты.
 *
 * @param orderReference    Уникальный номер заказа.
 * @param amount            Сумма оплаты.
 * @param paymentMethod     Метод оплаты.
 * @param customerFirstname Имя клиента.
 * @param customerLastname  Фамилия клиента.
 * @param customerEmail     Электронная почта клиента.
 */
public record PaymentConfirmation(
        String orderReference,
        BigDecimal amount,
        PaymentMethod paymentMethod,
        String customerFirstname,
        String customerLastname,
        String customerEmail
) {
}
