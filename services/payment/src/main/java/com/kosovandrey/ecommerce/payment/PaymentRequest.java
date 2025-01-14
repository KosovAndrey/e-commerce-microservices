package com.kosovandrey.ecommerce.payment;

import java.math.BigDecimal;

/**
 * Запрос на создание платежа.
 *
 * @param id             Уникальный идентификатор платежа.
 * @param amount         Сумма платежа.
 * @param paymentMethod  Метод оплаты.
 * @param orderId        Идентификатор заказа.
 * @param orderReference Уникальный идентификатор заказа.
 * @param customer       Информация о клиенте.
 */
public record PaymentRequest(
        Integer id,
        BigDecimal amount,
        PaymentMethod paymentMethod,
        Integer orderId,
        String orderReference,
        Customer customer
) {
}