package com.kosovandrey.ecommerce.orderline;

/**
 * Запрос на создание позиции заказа.
 *
 * @param id        Уникальный идентификатор позиции заказа (опционально для создания).
 * @param orderId   Идентификатор заказа.
 * @param productId Идентификатор товара.
 * @param quantity  Количество товара.
 */
public record OrderLineRequest(
        Integer id,
        Integer orderId,
        Integer productId,
        double quantity
) {
}