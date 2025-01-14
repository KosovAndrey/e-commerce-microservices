package com.kosovandrey.ecommerce.order;

import com.kosovandrey.ecommerce.product.PurchaseRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.List;

/**
 * Запрос на создание заказа.
 *
 * @param id            Уникальный идентификатор заказа (опционально для создания).
 * @param reference     Уникальный номер заказа.
 * @param amount        Общая сумма заказа. Должна быть положительной.
 * @param paymentMethod Метод оплаты. Обязательное поле.
 * @param customerId    Идентификатор клиента. Обязательное поле.
 * @param products      Список товаров в заказе. Должен содержать хотя бы один товар.
 */
public record OrderRequest(
        Integer id,

        String reference,

        @Positive(message = "Order amount should be positive")
        BigDecimal amount,

        @NotNull(message = "Payment method should be precised")
        PaymentMethod paymentMethod,

        @NotNull(message = "Customer should be present")
        @NotEmpty(message = "Customer should be present")
        @NotBlank(message = "Customer should be present")
        String customerId,

        @NotEmpty(message = "You should at least purchase one product")
        List<PurchaseRequest> products
) {
}