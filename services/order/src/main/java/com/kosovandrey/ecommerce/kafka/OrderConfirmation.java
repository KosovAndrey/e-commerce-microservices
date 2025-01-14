package com.kosovandrey.ecommerce.kafka;

import com.kosovandrey.ecommerce.customer.CustomerResponse;
import com.kosovandrey.ecommerce.order.PaymentMethod;
import com.kosovandrey.ecommerce.product.PurchaseResponse;

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
        CustomerResponse customer,
        List<PurchaseResponse> products
) {
}