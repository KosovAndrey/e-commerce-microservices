package com.kosovandrey.ecommerce.customer;

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
 * Контроллер для управления клиентами.
 */
@Slf4j
@RequiredArgsConstructor
@RestController
@Tag(name = "Customer API", description = "API для управления клиентами")
@RequestMapping("/api/v1/customers")
public class CustomerController {

    private final CustomerService service;

    /**
     * Создает нового клиента.
     *
     * @param request Данные для создания клиента.
     * @return Идентификатор созданного клиента.
     */
    @Operation(
            summary = "Создать клиента",
            description = "Создает нового клиента на основе переданных данных.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Клиент успешно создан"),
                    @ApiResponse(responseCode = "400", description = "Некорректные данные"),
                    @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера")
            }
    )
    @PostMapping
    public ResponseEntity<String> createCustomer(@RequestBody @Valid CustomerRequest request) {
        log.info("Received request to create customer");
        return ResponseEntity.ok(service.createCustomer(request));
    }

    /**
     * Обновляет данные существующего клиента.
     *
     * @param request Данные для обновления клиента.
     * @return Ответ с кодом.
     */
    @Operation(
            summary = "Обновить клиента",
            description = "Обновляет данные существующего клиента.",
            responses = {
                    @ApiResponse(responseCode = "202", description = "Запрос на обновление принят"),
                    @ApiResponse(responseCode = "400", description = "Некорректные данные"),
                    @ApiResponse(responseCode = "404", description = "Клиент не найден"),
                    @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера")
            }
    )
    @PutMapping
    public ResponseEntity<Void> updateCustomer(@RequestBody @Valid CustomerRequest request) {
        log.info("Received request to update customer with id: {}", request.id());
        service.updateCustomer(request);
        return ResponseEntity.accepted().build();
    }

    /**
     * Возвращает список всех клиентов.
     *
     * @return Список всех клиентов.
     */
    @Operation(
            summary = "Получить всех клиентов",
            description = "Возвращает список всех клиентов.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Список клиентов успешно получен"),
                    @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера")
            }
    )
    @GetMapping
    public ResponseEntity<List<CustomerResponse>> findAll() {
        log.info("Received request to fetch all customers");
        return ResponseEntity.ok(service.findAllCustomers());
    }

    /**
     * Проверяет существование клиента по его идентификатору.
     *
     * @param customerId Идентификатор клиента.
     * @return true, если клиент существует, иначе false.
     */
    @Operation(
            summary = "Проверить существование клиента",
            description = "Проверяет, существует ли клиент с указанным идентификатором.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Результат проверки"),
                    @ApiResponse(responseCode = "400", description = "Некорректный идентификатор клиента"),
                    @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера")
            }
    )
    @GetMapping("/exists/{customerId}")
    public ResponseEntity<Boolean> existsById(
            @Parameter(description = "Идентификатор клиента", required = true)
            @PathVariable String customerId
    ) {
        log.info("Checking if customer exists by id: {}", customerId);
        return ResponseEntity.ok(service.existsCustomerById(customerId));
    }

    /**
     * Возвращает клиента по его идентификатору.
     *
     * @param customerId Идентификатор клиента.
     * @return Данные клиента.
     */
    @Operation(
            summary = "Получить клиента по ID",
            description = "Возвращает данные клиента по его идентификатору.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Клиент успешно найден"),
                    @ApiResponse(responseCode = "400", description = "Некорректный идентификатор клиента"),
                    @ApiResponse(responseCode = "404", description = "Клиент не найден"),
                    @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера")
            }
    )
    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerResponse> findById(
            @Parameter(description = "Идентификатор клиента", required = true)
            @PathVariable String customerId
    ) {
        log.info("Fetching customer by id: {}", customerId);
        return ResponseEntity.ok(service.findCustomerById(customerId));
    }

    /**
     * Удаляет клиента по его идентификатору.
     *
     * @param customerId Идентификатор клиента.
     * @return Ответ с кодом.
     */
    @Operation(
            summary = "Удалить клиента",
            description = "Удаляет клиента по его идентификатору.",
            responses = {
                    @ApiResponse(responseCode = "202", description = "Запрос на удаление принят"),
                    @ApiResponse(responseCode = "400", description = "Некорректный идентификатор клиента"),
                    @ApiResponse(responseCode = "404", description = "Клиент не найден"),
                    @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера")
            }
    )
    @DeleteMapping("/{customerId}")
    public ResponseEntity<Void> deleteById(
            @Parameter(description = "Идентификатор клиента", required = true)
            @PathVariable String customerId
    ) {
        log.info("Deleting customer by id: {}", customerId);
        service.deleteCustomerById(customerId);
        return ResponseEntity.accepted().build();
    }
}