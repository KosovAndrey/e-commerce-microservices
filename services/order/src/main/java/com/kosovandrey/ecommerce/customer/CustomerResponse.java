package com.kosovandrey.ecommerce.customer;

/**
 * Ответ с информацией о клиенте.
 *
 * @param id        Уникальный идентификатор клиента.
 * @param firstname Имя клиента.
 * @param lastname  Фамилия клиента.
 * @param email     Электронная почта клиента.
 */
public record CustomerResponse(
        String id,
        String firstname,
        String lastname,
        String email
) {
}