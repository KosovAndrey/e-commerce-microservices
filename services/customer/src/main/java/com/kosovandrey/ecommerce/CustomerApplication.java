package com.kosovandrey.ecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * Основной класс приложения для микросервиса управления клиентами.
 * Запускает Spring Boot приложение и включает дополнительные функции:
 * - Кэширование с помощью {@link EnableCaching}.
 * - Асинхронную обработку с помощью {@link EnableAsync}.
 */
@SpringBootApplication
@EnableCaching
@EnableAsync
public class CustomerApplication {

    /**
     * Точка входа в приложение.
     *
     * @param args Аргументы командной строки.
     */
    public static void main(String[] args) {
        SpringApplication.run(CustomerApplication.class, args);
    }
}