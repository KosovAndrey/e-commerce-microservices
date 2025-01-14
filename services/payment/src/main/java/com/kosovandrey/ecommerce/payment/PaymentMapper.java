package com.kosovandrey.ecommerce.payment;

import org.springframework.stereotype.Service;

/**
 * Сервис для маппинга запросов на платежи в сущности платежей.
 */
@Service
public class PaymentMapper {

    /**
     * Преобразует запрос на платеж в сущность платежа.
     *
     * @param request Запрос на платеж.
     * @return Сущность платежа.
     */
    public Payment toPayment(PaymentRequest request) {
        return Payment.builder()
                .id(request.id())
                .amount(request.amount())
                .paymentMethod(request.paymentMethod())
                .orderId(request.orderId())
                .build();
    }
}