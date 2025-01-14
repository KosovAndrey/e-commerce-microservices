package com.kosovandrey.ecommerce.customer;

import lombok.*;
import org.springframework.validation.annotation.Validated;

/**
 * Сущность адреса.
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Validated
public class Address {
    private String street;      // Название улицы
    private String houseNumber; // Номер дома
    private String zipCode;     // Почтовый индекс
}