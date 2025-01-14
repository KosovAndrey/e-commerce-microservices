package com.kosovandrey.ecommerce.product;

import java.math.BigDecimal;

/**
 * Ответ с информацией о купленном товаре.
 *
 * @param id          Уникальный идентификатор товара.
 * @param name        Название товара.
 * @param description Описание товара.
 * @param price       Цена товара.
 * @param quantity    Количество товара.
 */
public record PurchaseResponse(
        Integer id,
        String name,
        String description,
        BigDecimal price,
        double quantity
) {
}