package com.kosovandrey.ecommerce.kafka.order;

import com.kosovandrey.ecommerce.kafka.payment.PaymentMethod;

import java.math.BigDecimal;
import java.util.List;

/**
 * Модель подтверждения заказа.
 *
 * @param orderReference Уникальный номер заказа.
 * @param amount         Общая сумма заказа.
 * @param paymentMethod  Метод оплаты.
 * @param customer       Информация о клиенте.
 * @param products       Список товаров в заказе.
 */
public record OrderConfirmation(
        String orderReference,
        BigDecimal amount,
        PaymentMethod paymentMethod,
        Customer customer,
        List<Product> products
) {
}
