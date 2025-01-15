package com.kosovandrey.ecommerce.handler;

import com.kosovandrey.ecommerce.exception.CustomerNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class GlobalExceptionHandlerTest {

    private final GlobalExceptionHandler handler = new GlobalExceptionHandler();

    /**
     * Проверяет, что метод handleCustomerNotFoundException возвращает статус 404 и сообщение об ошибке.
     */
    @Test
    void shouldHandleCustomerNotFoundException() {
        CustomerNotFoundException ex = new CustomerNotFoundException("Not found");

        ResponseEntity<String> response = handler.handleCustomerNotFoundException(ex);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Not found", response.getBody());
    }

    /**
     * Проверяет, что метод handleMethodArgumentNotValidException возвращает статус 400 и список ошибок.
     */
    @Test
    void shouldHandleMethodArgumentNotValidException() {
        // Создаем мок для MethodArgumentNotValidException
        MethodArgumentNotValidException ex = mock(MethodArgumentNotValidException.class);

        // Создаем мок для BindingResult
        BindingResult bindingResult = mock(BindingResult.class);

        // Создаем мок для FieldError
        FieldError fieldError1 = new FieldError("objectName", "field1", "Field1 is required");
        FieldError fieldError2 = new FieldError("objectName", "field2", "Field2 must be positive");

        // Настраиваем мок, чтобы getBindingResult() возвращал bindingResult
        when(ex.getBindingResult()).thenReturn(bindingResult);

        // Настраиваем мок, чтобы getAllErrors() возвращал список ошибок
        when(bindingResult.getAllErrors()).thenReturn(List.of(fieldError1, fieldError2));

        // Вызываем метод обработки исключения
        ResponseEntity<ErrorResponse> response = handler.handleMethodArgumentNotValidException(ex);

        // Проверяем, что статус ответа 400 (BAD_REQUEST)
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

        // Проверяем, что тело ответа не null
        assertNotNull(response.getBody());

        // Проверяем, что ошибки корректно добавлены в ответ
        var errors = response.getBody().errors();
        assertEquals(2, errors.size());
        assertEquals("Field1 is required", errors.get("field1"));
        assertEquals("Field2 must be positive", errors.get("field2"));
    }

    /**
     * Проверяет, что метод handleGenericException возвращает статус 500 и общее сообщение об ошибке.
     */
    @Test
    void shouldHandleGenericException() {
        Exception ex = new Exception("Unexpected error");

        ResponseEntity<String> response = handler.handleGenericException(ex);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("An unexpected error occurred", response.getBody());
    }
}