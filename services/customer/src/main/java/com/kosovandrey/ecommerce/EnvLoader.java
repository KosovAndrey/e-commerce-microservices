package com.kosovandrey.ecommerce;

import io.github.cdimascio.dotenv.Dotenv;

/**
 * Класс для загрузки переменных окружения из файла .env в системные свойства.
 * Поддерживает загрузку из файла по умолчанию (.env) или из указанного файла.
 */
public class EnvLoader {

    /**
     * Загружает переменные окружения из файла .env по умолчанию в системные свойства.
     * Если файл .env существует, все ключи и значения из него будут установлены как системные свойства.
     */
    public static void loadEnv() {
        Dotenv dotenv = Dotenv.configure().load();
        dotenv.entries().forEach(entry -> System.setProperty(entry.getKey(), entry.getValue()));
    }

    /**
     * Загружает переменные окружения из указанного файла в системные свойства.
     * Если файл существует, все ключи и значения из него будут установлены как системные свойства.
     *
     * @param filename имя файла, из которого будут загружены переменные окружения
     */
    public static void loadEnv(String filename) {
        Dotenv dotenv = Dotenv.configure()
                .filename(filename)
                .load();
        dotenv.entries().forEach(entry -> System.setProperty(entry.getKey(), entry.getValue()));
    }
}