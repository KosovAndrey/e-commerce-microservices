package com.kosovandrey.ecommerce.product;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Репозиторий для работы с товарами.
 */
public interface ProductRepository extends JpaRepository<Product, Long> {
    /**
     * Находит все товары по списку идентификаторов.
     *
     * @param productIds Список идентификаторов товаров.
     * @return Список товаров.
     */
    List<Product> findAllByIdInOrderById(List<Integer> productIds);
}