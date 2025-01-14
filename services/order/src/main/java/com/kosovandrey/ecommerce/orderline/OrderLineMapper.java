package com.kosovandrey.ecommerce.orderline;

import com.kosovandrey.ecommerce.order.Order;
import org.springframework.stereotype.Service;

/**
 * Сервис для преобразования объектов между слоями.
 */
@Service
public class OrderLineMapper {

    /**
     * Преобразует запрос в сущность позиции заказа.
     *
     * @param request Запрос на создание позиции заказа.
     * @return Сущность позиции заказа.
     */
    public OrderLine toOrderLine(OrderLineRequest request) {
        return OrderLine.builder()
                .id(request.id())
                .order(
                        Order.builder()
                                .id(request.orderId())
                                .build())
                .productId(request.productId())
                .quantity(request.quantity())
                .build();
    }

    /**
     * Преобразует сущность позиции заказа в ответ.
     *
     * @param orderLine Сущность позиции заказа.
     * @return Ответ с информацией о позиции заказа.
     */
    public OrderLineResponse fromOrderLine(OrderLine orderLine) {
        return new OrderLineResponse(
                orderLine.getId(),
                orderLine.getQuantity()
        );
    }
}