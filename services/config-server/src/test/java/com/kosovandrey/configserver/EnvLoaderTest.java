package com.kosovandrey.configserver;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileWriter;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * Тестовый класс для проверки функциональности класса EnvLoader.
 */
class EnvLoaderTest {

    private static final String TEST_ENV_FILE = ".env.test";

    /**
     * Очищает системные свойства перед каждым тестом.
     */
    @BeforeEach
    void cleanup() {
        System.clearProperty("TEST_KEY");
        System.clearProperty("TEST_KEY1");
        System.clearProperty("TEST_KEY2");
        System.clearProperty("MALFORMED_ENTRY");
    }

    /**
     * Проверяет загрузку переменных окружения из файла.
     *
     * @throws IOException если возникает ошибка при создании файла
     */
    @Test
    void shouldLoadEnvVariablesFromFile() throws IOException {
        createEnvFile(TEST_ENV_FILE, "TEST_KEY=TEST_VALUE");

        EnvLoader.loadEnv(TEST_ENV_FILE);

        assertEquals("TEST_VALUE", System.getProperty("TEST_KEY"));
    }

    /**
     * Проверяет обработку отсутствующего файла .env.
     */
    @Test
    void shouldHandleMissingEnvFile() {
        EnvLoader.loadEnv();

        assertNull(System.getProperty("TEST_KEY"));
    }

    /**
     * Проверяет обработку пустого файла .env.
     *
     * @throws IOException если возникает ошибка при создании файла
     */
    @Test
    void shouldHandleEmptyEnvFile() throws IOException {
        createEnvFile(TEST_ENV_FILE, "");

        EnvLoader.loadEnv(TEST_ENV_FILE);

        assertNull(System.getProperty("TEST_KEY"));

    }

    /**
     * Проверяет обработку некорректных записей в файле .env.
     *
     * @throws IOException если возникает ошибка при создании файла
     */
    @Test
    void shouldHandleMalformedEntries() throws IOException {
        createEnvFile(TEST_ENV_FILE, "MALFORMED_ENTRY");

        EnvLoader.loadEnv();

        assertNull(System.getProperty("MALFORMED_ENTRY"));
    }

    /**
     * Проверяет загрузку нескольких переменных окружения из файла.
     *
     * @throws IOException если возникает ошибка при создании файла
     */
    @Test
    void shouldHandleMultipleEnvVariables() throws IOException {
        createEnvFile(TEST_ENV_FILE, "TEST_KEY1=TEST_VALUE1\nTEST_KEY2=TEST_VALUE2");

        EnvLoader.loadEnv(TEST_ENV_FILE);

        assertEquals("TEST_VALUE1", System.getProperty("TEST_KEY1"));
        assertEquals("TEST_VALUE2", System.getProperty("TEST_KEY2"));
    }

    /**
     * Проверяет обработку пробелов вокруг значений в файле .env.
     *
     * @throws IOException если возникает ошибка при создании файла
     */
    @Test
    void shouldHandleWhitespaceInEnvFile() throws IOException {
        createEnvFile(TEST_ENV_FILE, "TEST_KEY = TEST_VALUE");

        EnvLoader.loadEnv(TEST_ENV_FILE);

        assertEquals("TEST_VALUE", System.getProperty("TEST_KEY"));
    }

    /**
     * Проверяет обработку комментариев в файле .env.
     *
     * @throws IOException если возникает ошибка при создании файла
     */
    @Test
    void shouldHandleCommentsInEnvFile() throws IOException {
        createEnvFile(TEST_ENV_FILE, "# Это комментарий\nTEST_KEY=TEST_VALUE");

        EnvLoader.loadEnv(TEST_ENV_FILE);

        assertEquals("TEST_VALUE", System.getProperty("TEST_KEY"));
    }

    /**
     * Проверяет обработку значений в кавычках в файле .env.
     *
     * @throws IOException если возникает ошибка при создании файла
     */
    @Test
    void shouldHandleQuotedValuesInEnvFile() throws IOException {
        createEnvFile(TEST_ENV_FILE, "TEST_KEY=\"TEST_VALUE\"");

        EnvLoader.loadEnv(TEST_ENV_FILE);

        assertEquals("TEST_VALUE", System.getProperty("TEST_KEY"));
    }

    /**
     * Проверяет обработку специальных символов в файле .env.
     *
     * @throws IOException если возникает ошибка при создании файла
     */
    @Test
    void shouldHandleSpecialCharactersInEnvFile() throws IOException {
        createEnvFile(TEST_ENV_FILE, "TEST_KEY=\"TEST_VALUE_WITH_SPECIAL_CHARS_!@#$%^&*()\"");

        EnvLoader.loadEnv(TEST_ENV_FILE);

        assertEquals("TEST_VALUE_WITH_SPECIAL_CHARS_!@#$%^&*()", System.getProperty("TEST_KEY"));
    }

    /**
     * Создает временный файл .env с указанным содержимым.
     *
     * @param filename имя файла
     * @param content  содержимое файла
     * @throws IOException если возникает ошибка при создании файла
     */
    private void createEnvFile(String filename, String content) throws IOException {
        try (FileWriter writer = new FileWriter(filename)) {
            writer.write(content);
        }
    }
}