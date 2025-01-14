package com.kosovandrey.ecommerce.order;

import org.springframework.stereotype.Service;

/**
 * Сервис для преобразования объектов между слоями.
 */
@Service
public class OrderMapper {

    /**
     * Преобразует запрос в сущность заказа.
     *
     * @param request Запрос на создание заказа.
     * @return Сущность заказа.
     */
    public Order toOrder(OrderRequest request) {
        return Order.builder()
                .id(request.id())
                .customerId(request.customerId())
                .reference(request.reference())
                .amount(request.amount())
                .paymentMethod(request.paymentMethod())
                .build();
    }

    /**
     * Преобразует сущность заказа в ответ.
     *
     * @param order Сущность заказа.
     * @return Ответ с информацией о заказе.
     */
    public OrderResponse fromOrder(Order order) {
        return new OrderResponse(
                order.getId(),
                order.getReference(),
                order.getAmount(),
                order.getPaymentMethod(),
                order.getCustomerId()
        );
    }
}