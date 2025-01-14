package com.kosovandrey.ecommerce.order;

/**
 * Перечисление методов оплаты.
 */
public enum PaymentMethod {
    CARD,   // Оплата банковской картой
    BTC,    // Оплата криптовалютой (Bitcoin)
    SBP     // Оплата через Систему быстрых платежей (СБП)
}