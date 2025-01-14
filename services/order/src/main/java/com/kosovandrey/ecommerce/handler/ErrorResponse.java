package com.kosovandrey.ecommerce.handler;

import java.util.Map;

/**
 * Ответ с ошибками валидации.
 *
 * @param errors Map, где ключ — это название поля, а значение — сообщение об ошибке.
 */
public record ErrorResponse(
        Map<String, String> errors
) {
}