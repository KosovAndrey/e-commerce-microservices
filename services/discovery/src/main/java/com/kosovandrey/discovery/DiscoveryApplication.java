package com.kosovandrey.discovery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Основной класс приложения для сервера обнаружения (Eureka Server).
 * Запускает Spring Boot приложение и включает дополнительные функции:
 * - Поддержку сервера обнаружения микросервисов с помощью {@link EnableEurekaServer}.
 */
@EnableEurekaServer
@SpringBootApplication
public class DiscoveryApplication {

	/**
	 * Точка входа в приложение.
	 *
	 * @param args Аргументы командной строки.
	 */
	public static void main(String[] args) {
		SpringApplication.run(DiscoveryApplication.class, args);
	}
}