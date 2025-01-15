package com.kosovandrey.ecommerce.customer;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Тестовый класс для проверки функциональности класса Customer.
 */
class CustomerTest {

    /**
     * Проверяет, что геттеры и сеттеры класса Customer работают корректно.
     */
    @Test
    void shouldCorrectlyGetAndSetCustomerFields() {
        Address address = new Address("Main St", "123", "12345");
        Customer customer = new Customer("1", "John", "Doe", "john.doe@example.com", address);

        assertEquals("1", customer.getId());
        assertEquals("John", customer.getFirstName());
        assertEquals("Doe", customer.getLastName());
        assertEquals("john.doe@example.com", customer.getEmail());
        assertEquals(address, customer.getAddress());

        customer.setId("2");
        customer.setFirstName("Jane");
        customer.setLastName("Smith");
        customer.setEmail("jane.smith@example.com");
        Address newAddress = new Address("Second St", "456", "67890");
        customer.setAddress(newAddress);

        assertEquals("2", customer.getId());
        assertEquals("Jane", customer.getFirstName());
        assertEquals("Smith", customer.getLastName());
        assertEquals("jane.smith@example.com", customer.getEmail());
        assertEquals(newAddress, customer.getAddress());
    }
}