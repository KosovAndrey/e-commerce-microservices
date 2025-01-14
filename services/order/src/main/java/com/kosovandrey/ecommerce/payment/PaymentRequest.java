package com.kosovandrey.ecommerce.payment;

import com.kosovandrey.ecommerce.customer.CustomerResponse;
import com.kosovandrey.ecommerce.order.PaymentMethod;

import java.math.BigDecimal;

/**
 * Запрос на оплату заказа.
 *
 * @param amount         Сумма оплаты.
 * @param paymentMethod  Метод оплаты.
 * @param orderId        Идентификатор заказа.
 * @param orderReference Уникальный номер заказа.
 * @param customer       Информация о клиенте.
 */
public record PaymentRequest(
        BigDecimal amount,
        PaymentMethod paymentMethod,
        Integer orderId,
        String orderReference,
        CustomerResponse customer
) {
}