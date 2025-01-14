package com.kosovandrey.ecommerce.product;

import java.math.BigDecimal;

/**
 * Ответ с информацией о товаре.
 *
 * @param id                  Идентификатор товара.
 * @param name                Название товара.
 * @param description         Описание товара.
 * @param availableQuantity   Доступное количество товара.
 * @param price               Цена товара.
 * @param categoryId          Идентификатор категории товара.
 * @param categoryName        Название категории товара.
 * @param categoryDescription Описание категории товара.
 */
public record ProductResponse(
        Integer id,
        String name,
        String description,
        double availableQuantity,
        BigDecimal price,
        Integer categoryId,
        String categoryName,
        String categoryDescription
) {
}