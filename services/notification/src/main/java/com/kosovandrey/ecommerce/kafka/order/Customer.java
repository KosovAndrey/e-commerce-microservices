package com.kosovandrey.ecommerce.kafka.order;

/**
 * Модель клиента.
 * Содержит информацию о клиенте, такую как идентификатор, имя, фамилия и электронная почта.
 *
 * @param id        Уникальный идентификатор клиента.
 * @param firstname Имя клиента.
 * @param lastname  Фамилия клиента.
 * @param email     Электронная почта клиента.
 */
public record Customer(
        String id,
        String firstname,
        String lastname,
        String email
) {
}
