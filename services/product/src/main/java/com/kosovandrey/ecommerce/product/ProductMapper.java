package com.kosovandrey.ecommerce.product;

import com.kosovandrey.ecommerce.category.Category;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

/**
 * Сервис для преобразования объектов запросов и ответов в сущности товаров.
 */
@Service
public class ProductMapper {
    /**
     * Преобразует запрос на создание товара в сущность товара.
     *
     * @param request Запрос на создание товара.
     * @return Сущность товара.
     */
    public Product toProduct(@Valid ProductRequest request) {
        return Product.builder()
                .id(request.id())
                .name(request.name())
                .description(request.description())
                .price(request.price())
                .availableQuantity(request.availableQuantity())
                .category(Category.builder()
                        .id(request.categoryId())
                        .build())
                .build();
    }

    /**
     * Преобразует сущность товара в ответ с информацией о товаре.
     *
     * @param product Сущность товара.
     * @return Ответ с информацией о товаре.
     */
    public ProductResponse toProductResponse(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getAvailableQuantity(),
                product.getPrice(),
                product.getCategory().getId(),
                product.getCategory().getName(),
                product.getCategory().getDescription()
        );
    }

    /**
     * Преобразует сущность товара и количество в ответ на покупку товара.
     *
     * @param product  Сущность товара.
     * @param quantity Количество товара.
     * @return Ответ на покупку товара.
     */
    public ProductPurchaseResponse toProductPurchaseResponse(Product product, double quantity) {
        return new ProductPurchaseResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                quantity
        );
    }
}