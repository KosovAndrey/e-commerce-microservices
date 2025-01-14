package com.kosovandrey.ecommerce.product;

import com.kosovandrey.ecommerce.exception.ProductPurchaseException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Сервис для управления товарами.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repository;
    private final ProductMapper mapper;

    /**
     * Создает новый товар.
     *
     * @param request Запрос на создание товара.
     * @return Идентификатор созданного товара.
     */
    public Integer createProduct(@Valid ProductRequest request) {
        log.info("Creating product with name: {}", request.name());
        Product product = mapper.toProduct(request);
        return repository.save(product).getId();
    }

    /**
     * Обрабатывает покупку товаров.
     *
     * @param request Список запросов на покупку товаров.
     * @return Список ответов с информацией о купленных товарах.
     */
    public List<ProductPurchaseResponse> purchaseProducts(List<ProductPurchaseRequest> request) {
        log.info("Processing purchase request for {} products", request.size());

        // Получаем список ID товаров из запроса
        var productIds = request.stream().map(ProductPurchaseRequest::productId).toList();

        // Находим все товары по их ID в базе данных
        var storedProducts = repository.findAllByIdInOrderById(productIds);

        // Проверяем, что все запрошенные товары существуют
        if (productIds.size() != storedProducts.size()) {
            log.error("One or more products do not exist in the database");
            throw new ProductPurchaseException("One or more products does not exists");
        }

        // Сортируем запросы по ID товара для синхронизации с данными из базы
        var storedRequest = request
                .stream()
                .sorted(Comparator.comparing(ProductPurchaseRequest::productId))
                .toList();

        // Список для хранения информации о купленных товарах
        var purchasedProducts = new ArrayList<ProductPurchaseResponse>();

        // Обрабатываем каждый товар
        for (int i = 0; i < storedProducts.size(); i++) {
            var product = storedProducts.get(i);
            var productRequest = storedRequest.get(i);

            // Проверяем, достаточно ли товара на складе
            if (product.getAvailableQuantity() < productRequest.quantity()) {
                log.error("Insufficient stock quantity for product with ID: {}", productRequest.productId());
                throw new ProductPurchaseException("Insufficient stock quantity for product with ID:: "
                        + productRequest.productId());
            }

            // Обновляем количество товара на складе
            var newAvailableQuantity = product.getAvailableQuantity() - productRequest.quantity();
            product.setAvailableQuantity(newAvailableQuantity);

            // Сохраняем обновленные данные в базе
            repository.save(product);

            // Добавляем информацию о купленном товаре в список
            purchasedProducts.add(mapper.toProductPurchaseResponse(product, productRequest.quantity()));
        }
        log.info("Purchase processed successfully for {} products", purchasedProducts.size());
        return purchasedProducts;
    }

    /**
     * Находит товар по идентификатору.
     *
     * @param productId Идентификатор товара.
     * @return Информация о товаре.
     */
    public ProductResponse findById(Long productId) {
        log.info("Fetching product with ID: {}", productId);
        return repository.findById(productId)
                .map(mapper::toProductResponse)
                .orElseThrow(() -> new EntityNotFoundException("Product not found with the ID:: " + productId));
    }

    /**
     * Возвращает список всех товаров.
     *
     * @return Список товаров.
     */
    public List<ProductResponse> findAll() {
        log.info("Fetching all products");
        return repository.findAll().stream().map(mapper::toProductResponse).collect(Collectors.toList());
    }
}