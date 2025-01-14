package com.kosovandrey.ecommerce.payment;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Репозиторий для работы с платежами.
 */
public interface PaymentRepository extends JpaRepository<Payment, Integer> {
}