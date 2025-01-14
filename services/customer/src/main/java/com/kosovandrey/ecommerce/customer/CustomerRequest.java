package com.kosovandrey.ecommerce.customer;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

/**
 * Запрос на создание или обновление клиента.
 * Содержит данные, необходимые для создания или обновления клиента.
 *
 * @param id        Уникальный идентификатор клиента (опционально для создания).
 * @param firstName Имя клиента. Обязательное поле.
 * @param lastName  Фамилия клиента. Обязательное поле.
 * @param email     Электронная почта клиента. Должна быть валидной. Обязательное поле.
 * @param address   Адрес клиента.
 */
public record CustomerRequest(
        String id,

        @NotNull(message = "Customer firstname is required")
        String firstName,

        @NotNull(message = "Customer lastname is required")
        String lastName,

        @NotNull(message = "Customer email is required")
        @Email(message = "Email should be valid")
        String email,

        Address address
) {
}