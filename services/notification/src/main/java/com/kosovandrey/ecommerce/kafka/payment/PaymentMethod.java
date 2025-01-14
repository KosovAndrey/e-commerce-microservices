package com.kosovandrey.ecommerce.kafka.payment;

/**
 * Перечисление методов оплаты.
 */
public enum PaymentMethod {
    CARD,   // Оплата банковской картой
    BTC,    // Оплата криптовалютой (Bitcoin)
    SBP     // Оплата через Систему быстрых платежей (СБП)
}