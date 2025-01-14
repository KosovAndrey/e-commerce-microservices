package com.kosovandrey.ecommerce.payment;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Feign-клиент для взаимодействия с микросервисом payment.
 */
@FeignClient(
        name = "payment-service",
        url = "${application.config.payment-url}"
)
public interface PaymentClient {

    /**
     * Отправляет запрос на оплату заказа.
     *
     * @param request Запрос на оплату.
     * @return Идентификатор платежа.
     */
    @PostMapping
    Integer requestOrderPayment(@RequestBody PaymentRequest request);
}