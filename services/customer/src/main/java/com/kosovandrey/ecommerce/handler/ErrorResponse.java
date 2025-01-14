package com.kosovandrey.ecommerce.handler;

import java.util.Map;

/**
 * Ответ с ошибками валидации.
 * Используется для возврата списка ошибок при невалидных входных данных.
 *
 * @param errors Map ошибок, где ключ — это поле, а значение — сообщение об ошибке.
 */
public record ErrorResponse(
        Map<String, String> errors
) {
}