package com.kosovandrey.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Основной класс приложения для API Gateway.
 * Запускает Spring Boot приложение, которое выступает в роли шлюза для маршрутизации запросов
 * между клиентами и микросервисами.
 */
@SpringBootApplication
public class GatewayApplication {

	/**
	 * Точка входа в приложение.
	 *
	 * @param args Аргументы командной строки.
	 */
	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}
}