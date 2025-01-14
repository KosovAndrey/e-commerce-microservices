package com.kosovandrey.ecommerce.customer;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Репозиторий для работы с клиентами.
 */
public interface CustomerRepository extends MongoRepository<Customer, String> {
}