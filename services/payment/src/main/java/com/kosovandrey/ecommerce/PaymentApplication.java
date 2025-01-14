package com.kosovandrey.ecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * Основной класс приложения для микросервиса управления платежами.
 * Запускает Spring Boot приложение и включает дополнительные функции:
 * - Поддержку JPA аудита для отслеживания изменений сущностей с помощью {@link EnableJpaAuditing}.
 */
@SpringBootApplication
@EnableJpaAuditing
public class PaymentApplication {

	/**
	 * Точка входа в приложение.
	 *
	 * @param args Аргументы командной строки.
	 */
	public static void main(String[] args) {
		SpringApplication.run(PaymentApplication.class, args);
	}
}