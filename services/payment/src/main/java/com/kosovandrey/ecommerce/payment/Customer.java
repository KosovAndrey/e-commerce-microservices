package com.kosovandrey.ecommerce.payment;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

/**
 * Модель клиента.
 *
 * @param id        Уникальный идентификатор клиента.
 * @param firstname Имя клиента.
 * @param lastname  Фамилия клиента.
 * @param email     Электронная почта клиента.
 */
@Validated
public record Customer(
        String id,

        @NotNull(message = "Firstname is required")
        String firstname,

        @NotNull(message = "Lastname is required")
        String lastname,

        @NotNull(message = "Email is required")
        @Email(message = "The email is not correctly formatted")
        String email
) {
}