package com.kosovandrey.ecommerce.orderline;

/**
 * Ответ с информацией о позиции заказа.
 *
 * @param id       Уникальный идентификатор позиции заказа.
 * @param quantity Количество товара.
 */
public record OrderLineResponse(
        Integer id,
        double quantity
) {
}