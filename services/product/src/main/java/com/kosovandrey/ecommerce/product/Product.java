package com.kosovandrey.ecommerce.product;

import com.kosovandrey.ecommerce.category.Category;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

/**
 * Сущность товара.
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class Product {

    @Id
    @GeneratedValue
    private Integer id;                  // Уникальный идентификатор товара

    private String name;                 // Название товара

    private String description;          // Описание товара

    private double availableQuantity;    // Доступное количество товара на складе

    private BigDecimal price;            // Цена товара

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;           // Категория товара
}