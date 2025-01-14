package com.kosovandrey.ecommerce.payment;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Контроллер для управления платежами.
 */
@Slf4j
@RequiredArgsConstructor
@RestController
@Tag(name = "Payment API", description = "API для управления платежами")
@RequestMapping("/api/v1/payments")
public class PaymentController {

    private final PaymentService service;

    /**
     * Создает новый платеж.
     *
     * @param request Запрос на создание платежа.
     * @return Идентификатор созданного платежа.
     */
    @PostMapping
    @Operation(
            summary = "Создать платеж",
            description = "Создает новый платеж на основе переданных данных.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Платеж успешно создан"),
                    @ApiResponse(responseCode = "400", description = "Некорректные данные"),
                    @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера")
            }
    )
    public ResponseEntity<Integer> createPayment(@RequestBody @Valid PaymentRequest request) {
        log.info("Received request to create payment for order: {}", request.orderReference());
        try {
            Integer paymentId = service.create(request);
            log.info("Payment created successfully with ID: {}", paymentId);
            return ResponseEntity.ok(paymentId);
        } catch (Exception ex) {
            log.error("Failed to create payment for order: {}", request.orderReference(), ex);
            return ResponseEntity.internalServerError().build();
        }
    }
}