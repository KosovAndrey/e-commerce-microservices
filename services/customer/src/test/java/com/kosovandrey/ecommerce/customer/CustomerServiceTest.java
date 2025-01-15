package com.kosovandrey.ecommerce.customer;

import com.kosovandrey.ecommerce.exception.CustomerNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    @Mock
    private CustomerRepository repository;

    @Mock
    private CustomerMapper mapper;

    @InjectMocks
    private CustomerService service;

    @Test
    void shouldReturnAllCustomers() {
        Address address = new Address("Main St", "123", "12345");
        Customer customer = new Customer("1", "John", "Doe", "john.doe@example.com", address);
        CustomerResponse response = new CustomerResponse("1", "John", "Doe", "john.doe@example.com", address);

        when(repository.findAll()).thenReturn(List.of(customer));
        when(mapper.fromCustomer(customer)).thenReturn(response);

        List<CustomerResponse> result = service.findAllCustomers();

        assertEquals(1, result.size());
        assertEquals(response, result.get(0));
        verify(repository, times(1)).findAll();
        verify(mapper, times(1)).fromCustomer(customer);
    }

    @Test
    void shouldReturnCustomerById() {
        Address address = new Address("Main St", "123", "12345");
        Customer customer = new Customer("1", "John", "Doe", "john.doe@example.com", address);
        CustomerResponse response = new CustomerResponse("1", "John", "Doe", "john.doe@example.com", address);

        when(repository.findById("1")).thenReturn(Optional.of(customer));
        when(mapper.fromCustomer(customer)).thenReturn(response);

        CustomerResponse result = service.findCustomerById("1");

        assertEquals(response, result);
        verify(repository, times(1)).findById("1");
        verify(mapper, times(1)).fromCustomer(customer);
    }

    @Test
    void shouldThrowExceptionWhenCustomerNotFoundById() {
        when(repository.findById("1")).thenReturn(Optional.empty());

        assertThrows(CustomerNotFoundException.class, () -> service.findCustomerById("1"));
        verify(repository, times(1)).findById("1");
    }

    @Test
    void shouldCreateCustomerAndReturnId() {
        Address address = new Address("Main St", "123", "12345");
        CustomerRequest request = new CustomerRequest("1", "John", "Doe", "john.doe@example.com", address);
        Customer customer = new Customer("1", "John", "Doe", "john.doe@example.com", address);

        when(mapper.toCustomer(request)).thenReturn(customer);
        when(repository.save(customer)).thenReturn(customer);

        String result = service.createCustomer(request);

        assertEquals("1", result);
        verify(mapper, times(1)).toCustomer(request);
        verify(repository, times(1)).save(customer);
    }

    @Test
    void shouldUpdateCustomer() {
        Address address = new Address("Main St", "123", "12345");
        CustomerRequest request = new CustomerRequest("1", "Jane", "Smith", "jane.smith@example.com", address);
        Customer customer = new Customer("1", "John", "Doe", "john.doe@example.com", address);

        when(repository.findById("1")).thenReturn(Optional.of(customer));
        when(repository.save(customer)).thenReturn(customer);

        service.updateCustomer(request);

        assertEquals("Jane", customer.getFirstName());
        assertEquals("Smith", customer.getLastName());
        assertEquals("jane.smith@example.com", customer.getEmail());
        assertEquals(address, customer.getAddress());

        verify(repository, times(1)).findById("1");
        verify(repository, times(1)).save(customer);
    }

    @Test
    void shouldThrowExceptionWhenUpdatingNonExistentCustomer() {
        Address address = new Address("Main St", "123", "12345");
        CustomerRequest request = new CustomerRequest("1", "John", "Doe", "john.doe@example.com", address);

        when(repository.findById("1")).thenReturn(Optional.empty());

        assertThrows(CustomerNotFoundException.class, () -> service.updateCustomer(request));
        verify(repository, times(1)).findById("1");
    }

    @Test
    void shouldDeleteCustomerById() {
        doNothing().when(repository).deleteById("1");

        service.deleteCustomerById("1");

        verify(repository, times(1)).deleteById("1");
    }

    @Test
    void shouldReturnTrueIfCustomerExistsById() {
        when(repository.existsById("1")).thenReturn(true);

        Boolean result = service.existsCustomerById("1");

        assertTrue(result);
        verify(repository, times(1)).existsById("1");
    }
}