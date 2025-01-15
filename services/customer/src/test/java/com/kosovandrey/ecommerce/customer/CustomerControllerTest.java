package com.kosovandrey.ecommerce.customer;

import com.kosovandrey.ecommerce.exception.CustomerNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomerControllerTest {


    @Mock
    private CustomerService service;

    @InjectMocks
    private CustomerController controller;

    @BeforeEach
    void setUp() {
        Mockito.reset(service);
    }

    /**
     * Проверяет, что метод createCustomer создает клиента и возвращает его идентификатор.
     */
    @Test
    void shouldCreateCustomerAndReturnId() {
        Address address = new Address("Main St", "123", "12345");
        CustomerRequest request = new CustomerRequest("1", "John", "Doe", "john.doe@example.com", address);

        when(service.createCustomer(request)).thenReturn("1");

        ResponseEntity<String> response = controller.createCustomer(request);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("1", response.getBody());
        verify(service, times(1)).createCustomer(request);
    }

    /**
     * Проверяет, что метод updateCustomer обновляет данные клиента и возвращает статус 202.
     */
    @Test
    void shouldUpdateCustomerAndReturnAcceptedStatus() {
        Address address = new Address("Main St", "123", "12345");
        CustomerRequest request = new CustomerRequest("1", "John", "Doe", "john.doe@example.com", address);

        ResponseEntity<Void> response = controller.updateCustomer(request);

        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
        verify(service, times(1)).updateCustomer(request);
    }

    /**
     * Проверяет, что метод findAll возвращает список всех клиентов.
     */
    @Test
    void shouldReturnAllCustomers() {
        Address address = new Address("Main St", "123", "12345");
        CustomerResponse response = new CustomerResponse("1", "John", "Doe", "john.doe@example.com", address);

        when(service.findAllCustomers()).thenReturn(List.of(response));

        ResponseEntity<List<CustomerResponse>> result = controller.findAll();

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(1, result.getBody().size());
        assertEquals(response, result.getBody().get(0));
        verify(service, times(1)).findAllCustomers();
    }

    /**
     * Проверяет, что метод existsById возвращает true, если клиент существует.
     */
    @Test
    void shouldReturnTrueIfCustomerExistsById() {
        when(service.existsCustomerById("1")).thenReturn(true);

        ResponseEntity<Boolean> response = controller.existsById("1");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody());
        verify(service, times(1)).existsCustomerById("1");
    }

    /**
     * Проверяет, что метод findById возвращает клиента по его идентификатору.
     */
    @Test
    void shouldReturnCustomerById() {
        Address address = new Address("Main St", "123", "12345");
        CustomerResponse response = new CustomerResponse("1", "John", "Doe", "john.doe@example.com", address);

        when(service.findCustomerById("1")).thenReturn(response);

        ResponseEntity<CustomerResponse> result = controller.findById("1");

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(response, result.getBody());
        verify(service, times(1)).findCustomerById("1");
    }

    /**
     * Проверяет, что метод findById выбрасывает исключение, если клиент не найден.
     */
    @Test
    void shouldThrowExceptionWhenCustomerNotFoundById() {
        when(service.findCustomerById("1")).thenThrow(new CustomerNotFoundException("Not found"));

        assertThrows(CustomerNotFoundException.class, () -> controller.findById("1"));
        verify(service, times(1)).findCustomerById("1");
    }

    /**
     * Проверяет, что метод deleteById удаляет клиента и возвращает статус 202.
     */
    @Test
    void shouldDeleteCustomerAndReturnAcceptedStatus() {
        ResponseEntity<Void> response = controller.deleteById("1");

        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
        verify(service, times(1)).deleteCustomerById("1");
    }
}