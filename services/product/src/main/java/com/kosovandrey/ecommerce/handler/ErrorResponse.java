package com.kosovandrey.ecommerce.handler;

import java.util.Map;

/**
 * Ответ с ошибками валидации.
 *
 * @param errors Map ошибок, где ключ — название поля, а значение — сообщение об ошибке.
 */
public record ErrorResponse(
        Map<String, String> errors
) {
}