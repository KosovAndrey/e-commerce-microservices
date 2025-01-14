package com.kosovandrey.ecommerce.product;

import java.math.BigDecimal;

/**
 * Ответ на покупку товара.
 *
 * @param id          Идентификатор товара.
 * @param name        Название товара.
 * @param description Описание товара.
 * @param price       Цена товара.
 * @param quantity    Количество товара.
 */
public record ProductPurchaseResponse(
        Integer id,
        String name,
        String description,
        BigDecimal price,
        double quantity
) {
}