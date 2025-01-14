package com.kosovandrey.ecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * Основной класс приложения для уведомлений.
 * Запускает Spring Boot приложение и включает дополнительные функции:
 * - Асинхронную обработку с помощью {@link EnableAsync}.
 */
@SpringBootApplication
@EnableAsync
public class NotificationApplication {

    /**
     * Точка входа в приложение.
     *
     * @param args Аргументы командной строки.
     */
    public static void main(String[] args) {
        SpringApplication.run(NotificationApplication.class, args);
    }
}