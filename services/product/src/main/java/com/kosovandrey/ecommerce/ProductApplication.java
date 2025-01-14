package com.kosovandrey.ecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Основной класс приложения для микросервиса управления товарами.
 */
@SpringBootApplication
public class ProductApplication {

	/**
	 * Точка входа в приложение.
	 *
	 * @param args Аргументы командной строки.
	 */
	public static void main(String[] args) {
		SpringApplication.run(ProductApplication.class, args);
	}
}