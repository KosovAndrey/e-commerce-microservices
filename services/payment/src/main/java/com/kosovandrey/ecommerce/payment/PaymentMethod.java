package com.kosovandrey.ecommerce.payment;

/**
 * Перечисление методов оплаты.
 */
public enum PaymentMethod {
    CARD,   // Оплата банковской картой
    BTC,    // Оплата криптовалютой (Bitcoin)
    SBP     // Оплата через Систему быстрых платежей (СБП)
}