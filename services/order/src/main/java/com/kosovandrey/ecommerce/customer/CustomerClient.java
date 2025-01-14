package com.kosovandrey.ecommerce.customer;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

/**
 * Feign-клиент для взаимодействия с микросервисом customer.
 */
@FeignClient(
        name = "customer-service",
        url = "${application.config.customer-url}"
)
public interface CustomerClient {

    /**
     * Получает информацию о клиенте по его идентификатору.
     *
     * @param customerId Идентификатор клиента.
     * @return Информация о клиенте в виде {@link Optional<CustomerResponse>}.
     */
    @GetMapping("/{customer-id}")
    Optional<CustomerResponse> findCustomerById(@PathVariable("customer-id") String customerId);
}
