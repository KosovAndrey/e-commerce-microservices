package com.kosovandrey.ecommerce.product;

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
 * Контроллер для управления товарами.
 */
@Slf4j
@RequiredArgsConstructor
@RestController
@Tag(name = "Product API", description = "API для управления товарами")
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService service;

    /**
     * Создает новый товар.
     *
     * @param request Запрос на создание товара.
     * @return Идентификатор созданного товара.
     */
    @PostMapping
    @Operation(
            summary = "Создать товар",
            description = "Создает новый товар на основе переданных данных.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Товар успешно создан"),
                    @ApiResponse(responseCode = "400", description = "Некорректные данные"),
                    @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера")
            }
    )
    public ResponseEntity<Integer> createProduct(@RequestBody @Valid ProductRequest request) {
        log.info("Creating product with name: {}", request.name());
        return ResponseEntity.ok(service.createProduct(request));
    }

    /**
     * Обрабатывает покупку товаров.
     *
     * @param request Список запросов на покупку товаров.
     * @return Список ответов с информацией о купленных товарах.
     */
    @PostMapping("/purchase")
    @Operation(
            summary = "Купить товары",
            description = "Обрабатывает покупку товаров на основе переданных данных.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Покупка успешно обработана"),
                    @ApiResponse(responseCode = "400", description = "Некорректные данные"),
                    @ApiResponse(responseCode = "404", description = "Товар не найден"),
                    @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера")
            }
    )
    public ResponseEntity<List<ProductPurchaseResponse>> purchaseProducts(
            @RequestBody List<ProductPurchaseRequest> request
    ) {
        log.info("Processing purchase request for {} products", request.size());
        return ResponseEntity.ok(service.purchaseProducts(request));
    }

    /**
     * Находит товар по идентификатору.
     *
     * @param productId Идентификатор товара.
     * @return Информация о товаре.
     */
    @GetMapping("/{product-id}")
    @Operation(
            summary = "Найти товар по ID",
            description = "Возвращает информацию о товаре по его идентификатору.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Товар успешно найден"),
                    @ApiResponse(responseCode = "400", description = "Некорректный идентификатор товара"),
                    @ApiResponse(responseCode = "404", description = "Товар не найден"),
                    @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера")
            }
    )
    public ResponseEntity<ProductResponse> findById(
            @Parameter(description = "Идентификатор товара", required = true)
            @PathVariable("product-id") Long productId
    ) {
        log.info("Fetching product with ID: {}", productId);
        return ResponseEntity.ok(service.findById(productId));
    }

    /**
     * Возвращает список всех товаров.
     *
     * @return Список товаров.
     */
    @GetMapping
    @Operation(
            summary = "Получить все товары",
            description = "Возвращает список всех товаров.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Список товаров успешно получен"),
                    @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера")
            }
    )
    public ResponseEntity<List<ProductResponse>> findAll() {
        log.info("Fetching all products");
        return ResponseEntity.ok(service.findAll());
    }
}