package com.kosovandrey.ecommerce.customer;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CustomerMapperTest {

    private final CustomerMapper mapper = new CustomerMapper();

    /**
     * Проверяет, что метод toCustomer корректно преобразует CustomerRequest в Customer.
     */
    @Test
    void shouldCorrectlyMapCustomerRequestToCustomer() {
        Address address = new Address("Main St", "123", "12345");
        CustomerRequest request = new CustomerRequest("1", "John", "Doe", "john.doe@example.com", address);

        Customer customer = mapper.toCustomer(request);

        assertEquals("1", customer.getId());
        assertEquals("John", customer.getFirstName());
        assertEquals("Doe", customer.getLastName());
        assertEquals("john.doe@example.com", customer.getEmail());
        assertEquals(address, customer.getAddress());
    }

    /**
     * Проверяет, что метод fromCustomer корректно преобразует Customer в CustomerResponse.
     */
    @Test
    void shouldCorrectlyMapCustomerToCustomerResponse() {
        Address address = new Address("Main St", "123", "12345");
        Customer customer = new Customer("1", "John", "Doe", "john.doe@example.com", address);

        CustomerResponse response = mapper.fromCustomer(customer);

        assertEquals("1", response.id());
        assertEquals("John", response.firstName());
        assertEquals("Doe", response.lastName());
        assertEquals("john.doe@example.com", response.email());
        assertEquals(address, response.address());
    }
}