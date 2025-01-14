package com.kosovandrey.ecommerce.payment;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Сущность платежа.
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "payment")
public class Payment {
    @Id
    @GeneratedValue
    private Integer id;                  // Уникальный идентификатор платежа

    private BigDecimal amount;           // Сумма платежа

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod; // Метод оплаты (например, CARD, BTC, SBP)

    private Integer orderId;             // Идентификатор заказа, связанного с платежом

    @CreatedDate
    @Column(updatable = false, nullable = false)
    private LocalDateTime createdAt;     // Дата и время создания платежа

    @LastModifiedDate
    @Column(updatable = false)
    private LocalDateTime lastModifiedDate; // Дата и время последнего изменения платежа
}