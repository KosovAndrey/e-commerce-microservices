package com.kosovandrey.configserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * Основной класс приложения для сервера конфигураций.
 * Запускает Spring Boot приложение и включает дополнительные функции:
 * - Поддержку централизованного управления конфигурациями с помощью {@link EnableConfigServer}.
 */
@EnableConfigServer
@SpringBootApplication
public class ConfigServerApplication {

    /**
     * Точка входа в приложение.
     *
     * @param args Аргументы командной строки.
     */
    public static void main(String[] args) {
        SpringApplication.run(ConfigServerApplication.class, args);
    }

}
