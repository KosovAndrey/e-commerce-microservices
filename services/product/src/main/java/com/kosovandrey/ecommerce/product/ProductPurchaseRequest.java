package com.kosovandrey.ecommerce.product;

import jakarta.validation.constraints.NotNull;

/**
 * Запрос на покупку товара.
 *
 * @param productId Идентификатор товара.
 * @param quantity  Количество товара.
 */
public record ProductPurchaseRequest(
        @NotNull(message = "Product is mandatory")
        Integer productId,
        @NotNull(message = "Quantity is mandatory")
        double quantity
) {
}