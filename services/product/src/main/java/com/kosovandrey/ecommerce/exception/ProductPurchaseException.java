package com.kosovandrey.ecommerce.exception;

/**
 * Исключение, возникающее при ошибке покупки товара.
 */
public class ProductPurchaseException extends RuntimeException {

    /**
     * Создает исключение с указанным сообщением.
     *
     * @param message Сообщение об ошибке.
     */
    public ProductPurchaseException(String message) {
        super(message);
    }
}