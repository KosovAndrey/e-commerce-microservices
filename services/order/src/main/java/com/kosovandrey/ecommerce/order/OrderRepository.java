package com.kosovandrey.ecommerce.order;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Репозиторий для работы с заказами.
 */
public interface OrderRepository extends JpaRepository<Order, Integer> {
}