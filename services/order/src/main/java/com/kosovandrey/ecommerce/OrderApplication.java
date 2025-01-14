package com.kosovandrey.ecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * Основной класс приложения для микросервиса управления заказами.
 * Запускает Spring Boot приложение и включает дополнительные функции:
 * - Интеграцию с Feign для взаимодействия с другими микросервисами с помощью {@link EnableFeignClients}.
 * - Поддержку JPA аудита для отслеживания изменений сущностей с помощью {@link EnableJpaAuditing}.
 */
@SpringBootApplication
@EnableFeignClients
@EnableJpaAuditing
public class OrderApplication {

    /**
     * Точка входа в приложение.
     *
     * @param args Аргументы командной строки.
     */
    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }
}