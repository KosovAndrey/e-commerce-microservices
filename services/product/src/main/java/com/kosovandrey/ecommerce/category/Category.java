package com.kosovandrey.ecommerce.category;

import com.kosovandrey.ecommerce.product.Product;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

/**
 * Сущность категории товаров.
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class Category {
    @Id
    @GeneratedValue
    private Integer id;                // Уникальный идентификатор категории

    private String name;               // Название категории

    private String description;        // Описание категории

    @OneToMany(mappedBy = "category", cascade = CascadeType.REMOVE)
    private List<Product> products;    // Список товаров, принадлежащих этой категории
}
