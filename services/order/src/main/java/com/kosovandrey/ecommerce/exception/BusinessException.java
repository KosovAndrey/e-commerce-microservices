package com.kosovandrey.ecommerce.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Исключение, связанное с бизнес-логикой приложения.
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class BusinessException extends RuntimeException {
    private final String message;  // Сообщение об ошибке
}