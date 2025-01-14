package com.kosovandrey.ecommerce.orderline;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Сервис для управления позициями заказа.
 */
@Service
@RequiredArgsConstructor
public class OrderLineService {

    private final OrderLineRepository repository;
    private final OrderLineMapper mapper;

    /**
     * Сохраняет позицию заказа.
     *
     * @param request Запрос на создание позиции заказа.
     * @return Идентификатор созданной позиции заказа.
     */
    public Integer saveOrderLine(OrderLineRequest request) {
        var order = mapper.toOrderLine(request);
        return repository.save(order).getId();
    }

    /**
     * Возвращает список позиций заказа по идентификатору заказа.
     *
     * @param orderId Идентификатор заказа.
     * @return Список позиций заказа.
     */
    public List<OrderLineResponse> findAllByOrderId(Integer orderId) {
        return repository.findAllByOrderId(orderId).stream().map(mapper::fromOrderLine).toList();
    }
}