package com.kosovandrey.ecommerce.order;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Контроллер для управления заказами.
 */
@Slf4j
@RequiredArgsConstructor
@RestController
@Tag(name = "Order API", description = "API для управления заказами")
@RequestMapping("/api/v1/orders")
public class OrderController {

    private final OrderService service;

    /**
     * Создает новый заказ.
     *
     * @param request Запрос на создание заказа.
     * @return Идентификатор созданного заказа.
     */
    @PostMapping
    @Operation(
            summary = "Создать заказ",
            description = "Создает новый заказ на основе переданных данных.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Заказ успешно создан"),
                    @ApiResponse(responseCode = "400", description = "Некорректные данные"),
                    @ApiResponse(responseCode = "404", description = "Клиент или товар не найден"),
                    @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера")
            }
    )
    public ResponseEntity<Integer> createOrder(@RequestBody @Valid OrderRequest request) {
        log.info("Received request to create order for customer: {}", request.customerId());
        return ResponseEntity.ok(service.createOrder(request));
    }

    /**
     * Возвращает список всех заказов.
     *
     * @return Список заказов.
     */
    @GetMapping
    @Operation(
            summary = "Получить все заказы",
            description = "Возвращает список всех заказов.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Список заказов успешно получен"),
                    @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера")
            }
    )
    public ResponseEntity<List<OrderResponse>> findAll() {
        log.info("Fetching all orders");
        return ResponseEntity.ok(service.findAll());
    }

    /**
     * Возвращает заказ по его идентификатору.
     *
     * @param orderId Идентификатор заказа.
     * @return Информация о заказе.
     */
    @GetMapping("/{order-id}")
    @Operation(
            summary = "Найти заказ по ID",
            description = "Возвращает информацию о заказе по его идентификатору.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Заказ успешно найден"),
                    @ApiResponse(responseCode = "400", description = "Некорректный идентификатор заказа"),
                    @ApiResponse(responseCode = "404", description = "Заказ не найден"),
                    @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера")
            }
    )
    public ResponseEntity<OrderResponse> findById(
            @Parameter(description = "Идентификатор заказа", required = true)
            @PathVariable("order-id") Integer orderId
    ) {
        log.info("Fetching order by ID: {}", orderId);
        return ResponseEntity.ok(service.findById(orderId));
    }
}