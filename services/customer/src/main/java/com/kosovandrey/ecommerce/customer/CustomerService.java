package com.kosovandrey.ecommerce.customer;

import com.kosovandrey.ecommerce.exception.CustomerNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Сервис для управления клиентами.
 * Обеспечивает создание, обновление, поиск и удаление клиентов.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository repository;
    private final CustomerMapper mapper;

    /**
     * Возвращает список всех клиентов.
     *
     * @return список всех клиентов в виде {@link CustomerResponse}
     */
    public List<CustomerResponse> findAllCustomers() {
        log.info("Fetching all customers");
        return repository.findAll()
                .stream()
                .map(mapper::fromCustomer)
                .collect(Collectors.toList());
    }

    /**
     * Возвращает клиента по его идентификатору. Результат кэшируется.
     *
     * @param customerId идентификатор клиента
     * @return данные клиента в виде {@link CustomerResponse}
     * @throws CustomerNotFoundException если клиент с указанным идентификатором не найден
     */
    @Cacheable(value = "customer", key = "#customerId")
    public CustomerResponse findCustomerById(String customerId) {
        log.info("Fetching customer by id: {}", customerId);
        return repository.findById(customerId)
                .map(mapper::fromCustomer)
                .orElseThrow(() -> new CustomerNotFoundException("No customer found with id " + customerId));
    }

    /**
     * Создает нового клиента.
     *
     * @param request данные для создания клиента
     * @return идентификатор созданного клиента
     */
    public String createCustomer(CustomerRequest request) {
        log.info("Creating customer with email: {}", request.email());
        return repository.save(mapper.toCustomer(request)).getId();
    }

    /**
     * Обновляет данные существующего клиента.
     *
     * @param request данные для обновления клиента
     * @throws CustomerNotFoundException если клиент с указанным идентификатором не найден
     */
    public void updateCustomer(CustomerRequest request) {
        log.info("Updating customer with id: {}", request.id());
        var customer = repository.findById(request.id())
                .orElseThrow(() -> new CustomerNotFoundException("No customer found with id " + request.id()));
        mergeCustomerData(customer, request);
        repository.save(customer);
    }

    /**
     * Удаляет клиента по его идентификатору. Очищает кэш, связанный с клиентами.
     *
     * @param customerId идентификатор клиента
     */
    @CacheEvict(value = {"customers", "customer"}, allEntries = true)
    public void deleteCustomerById(String customerId) {
        log.info("Deleting customer with id: {}", customerId);
        repository.deleteById(customerId);
    }

    /**
     * Проверяет существование клиента по его идентификатору.
     *
     * @param customerId идентификатор клиента
     * @return true, если клиент существует, иначе false
     */
    public Boolean existsCustomerById(String customerId) {
        return repository.existsById(customerId);
    }

    /**
     * Обновляет данные клиента на основе данных из запроса.
     *
     * @param customer клиент, данные которого нужно обновить
     * @param request  данные для обновления
     */
    void mergeCustomerData(Customer customer, CustomerRequest request) {
        if (request.firstName() != null) customer.setFirstName(request.firstName());
        if (request.lastName() != null) customer.setLastName(request.lastName());
        if (request.email() != null) customer.setEmail(request.email());
        if (request.address() != null) customer.setAddress(request.address());
    }
}