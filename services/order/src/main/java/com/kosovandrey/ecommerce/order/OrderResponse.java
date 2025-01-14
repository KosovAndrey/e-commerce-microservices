package com.kosovandrey.ecommerce.order;

import java.math.BigDecimal;

/**
 * Ответ с информацией о заказе.
 *
 * @param id            Уникальный идентификатор заказа.
 * @param reference     Уникальный номер заказа.
 * @param amount        Общая сумма заказа.
 * @param paymentMethod Метод оплаты.
 * @param customerId    Идентификатор клиента.
 */
public record OrderResponse(
        Integer id,
        String reference,
        BigDecimal amount,
        PaymentMethod paymentMethod,
        String customerId
) {
}