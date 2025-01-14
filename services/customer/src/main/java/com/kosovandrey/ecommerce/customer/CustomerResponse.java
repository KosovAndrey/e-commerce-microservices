package com.kosovandrey.ecommerce.customer;

/**
 * Ответ с данными клиента.
 * Содержит информацию о клиенте, которая возвращается в ответ на запрос.
 *
 * @param id        Уникальный идентификатор клиента.
 * @param firstName Имя клиента.
 * @param lastName  Фамилия клиента.
 * @param email     Электронная почта клиента.
 * @param address   Адрес клиента.
 */
public record CustomerResponse(
        String id,
        String firstName,
        String lastName,
        String email,
        Address address
) {
}