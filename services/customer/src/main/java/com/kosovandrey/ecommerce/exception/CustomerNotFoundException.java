package com.kosovandrey.ecommerce.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Исключение, выбрасываемое при отсутствии клиента.
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CustomerNotFoundException extends RuntimeException {
    private final String message;  // Сообщение об ошибке
}