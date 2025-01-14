package com.kosovandrey.ecommerce.order;

import com.kosovandrey.ecommerce.orderline.OrderLine;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Сущность заказа.
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "customer_order")
public class Order {
    @Id
    @GeneratedValue
    private Integer id;                       // Уникальный идентификатор заказа

    private String reference;                 // Уникальный номер заказа

    private BigDecimal amount;                // Общая сумма заказа

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;      // Метод оплаты

    private String customerId;                // Идентификатор клиента

    @OneToMany(mappedBy = "order")
    private List<OrderLine> orderLines;       // Список позиций заказа

    @CreatedDate
    @Column(updatable = false, nullable = false)
    private LocalDateTime createdAt;          // Дата создания заказа

    @LastModifiedDate
    @Column(updatable = false)
    private LocalDateTime lastModifiedDate;   // Дата последнего изменения заказа
}