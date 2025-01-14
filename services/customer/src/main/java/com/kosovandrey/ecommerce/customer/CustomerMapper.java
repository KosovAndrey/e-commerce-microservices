package com.kosovandrey.ecommerce.customer;

import org.springframework.stereotype.Service;

/**
 * Маппер для преобразования объектов между слоями.
 */
@Service
public class CustomerMapper {

    /**
     * Преобразует запрос на создание клиента в сущность {@link Customer}.
     *
     * @param request Запрос на создание клиента.
     * @return Сущность {@link Customer}.
     */
    public Customer toCustomer(CustomerRequest request) {
        return Customer.builder()
                .id(request.id())
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .address(request.address())
                .build();
    }

    /**
     * Преобразует сущность {@link Customer} в ответ {@link CustomerResponse}.
     *
     * @param customer Сущность клиента.
     * @return Ответ с данными клиента.
     */
    public CustomerResponse fromCustomer(Customer customer) {
        return new CustomerResponse(
                customer.getId(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getEmail(),
                customer.getAddress()
        );
    }
}