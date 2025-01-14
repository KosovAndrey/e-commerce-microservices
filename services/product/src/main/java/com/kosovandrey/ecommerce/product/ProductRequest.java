package com.kosovandrey.ecommerce.product;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

/**
 * Запрос на создание или обновление товара.
 *
 * @param id                Идентификатор товара.
 * @param name              Название товара.
 * @param description       Описание товара.
 * @param availableQuantity Доступное количество товара.
 * @param price             Цена товара.
 * @param categoryId        Идентификатор категории товара.
 */
public record ProductRequest(
        Integer id,

        @NotNull(message = "Product name is required")
        String name,

        @NotNull(message = "Product description is required")
        String description,

        @Positive(message = "Available quantity should be positive")
        double availableQuantity,

        @Positive(message = "Price should be positive")
        BigDecimal price,

        @NotNull(message = "Product category is required")
        Integer categoryId
) {
}