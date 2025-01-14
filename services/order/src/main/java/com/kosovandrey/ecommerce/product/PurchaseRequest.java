package com.kosovandrey.ecommerce.product;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

/**
 * Запрос на покупку товара.
 *
 * @param productId Идентификатор товара. Обязательное поле.
 * @param quantity  Количество товара. Должно быть положительным.
 */
public record PurchaseRequest(
        @NotNull(message = "Product is mandatory")
        Integer productId,

        @Positive(message = "Quantity is mandatory")
        double quantity
) {
}