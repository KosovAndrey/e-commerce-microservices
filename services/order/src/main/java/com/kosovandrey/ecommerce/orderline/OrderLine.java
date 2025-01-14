package com.kosovandrey.ecommerce.orderline;

import com.kosovandrey.ecommerce.order.Order;
import jakarta.persistence.*;
import lombok.*;

/**
 * Сущность позиции заказа.
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class OrderLine {
    @Id
    @GeneratedValue
    private Integer id;          // Уникальный идентификатор позиции заказа
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;         // Заказ, к которому относится позиция
    private Integer productId;   // Идентификатор товара
    private double quantity;     // Количество товара
}