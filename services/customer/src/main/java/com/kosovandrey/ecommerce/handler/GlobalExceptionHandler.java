package com.kosovandrey.ecommerce.handler;

import com.kosovandrey.ecommerce.exception.CustomerNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;

/**
 * Глобальный обработчик исключений для REST-контроллеров.
 * Перехватывает исключения и возвращает соответствующие HTTP-ответы.
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Конструктор для инициализации обработчика исключений.
     *
     * @param error Представление для отображения ошибок.
     */

    /**
     * Обрабатывает исключение {@link CustomerNotFoundException}.
     *
     * @param ex Исключение, связанное с отсутствием клиента.
     * @return Ответ с кодом 404 (Not Found) и сообщением об ошибке.
     */
    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<String> handleCustomerNotFoundException(CustomerNotFoundException ex) {
        log.error("Customer not found: {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    /**
     * Обрабатывает исключение {@link MethodArgumentNotValidException}.
     * Возникает при ошибках валидации входных данных.
     *
     * @param ex Исключение, связанное с невалидными аргументами.
     * @return Ответ с кодом 400 (Bad Request) и списком ошибок валидации.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        log.error("Validation error: {}", ex.getMessage());
        var errors = new HashMap<String, String>();
        ex.getBindingResult().getAllErrors()
                .forEach(error -> {
                            var fieldName = ((FieldError) error).getField();
                            var errorMessage = error.getDefaultMessage();
                            errors.put(fieldName, errorMessage);
                        }
                );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(errors));
    }

    /**
     * Обрабатывает все непредвиденные исключения.
     *
     * @param ex Исключение, которое не было обработано другими обработчиками.
     * @return Ответ с кодом 500 (Internal Server Error) и общим сообщением об ошибке.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception ex) {
        log.error("Unexpected error occurred: {}", ex.getMessage(), ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred");
    }
}