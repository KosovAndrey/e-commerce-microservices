package com.kosovandrey.ecommerce.kafka.order;

import java.math.BigDecimal;

/**
 * Модель товара.
 *
 * @param id          Уникальный идентификатор товара.
 * @param name        Название товара.
 * @param description Описание товара.
 * @param price       Цена товара.
 * @param quantity    Количество товара.
 */
public record Product(
        Integer id,
        String name,
        String description,
        BigDecimal price,
        double quantity
) {
}
