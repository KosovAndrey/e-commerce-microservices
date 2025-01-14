package com.kosovandrey.ecommerce.customer;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Сущность клиента.
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Document
public class Customer {
    @Id
    private String id;          // Уникальный идентификатор клиента
    private String firstName;   // Имя клиента
    private String lastName;    // Фамилия клиента
    private String email;       // Электронная почта клиента
    private Address address;    // Адрес клиента
}
