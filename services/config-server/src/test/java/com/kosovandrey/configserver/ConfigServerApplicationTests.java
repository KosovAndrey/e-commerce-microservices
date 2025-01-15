package com.kosovandrey.configserver;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(classes = ConfigServerApplication.class)
class ConfigServerApplicationTests {

	/**
	 * Метод, выполняющий настройку перед запуском всех тестов.
	 * Загружает переменные среды из файла `.env` и устанавливает их в системные свойства,
	 * чтобы они были доступны в тестах.
	 *
	 * @see EnvLoader#loadEnv()
	 */
	@BeforeAll
	public static void setup() {
		EnvLoader.loadEnv();
	}

	/**
	 * Тест проверяет, что контекст Spring Boot приложения успешно запускается.
	 */
	@Test
	public void contextShouldLoads() {
		// Если контекст успешно запускается, тест считается пройденным.
	}

	/**
	 * Проверяет, что метод main запускает приложение без ошибок.
	 */
	@Test
	void shouldRunMainMethodWithoutErrors() {
		ConfigServerApplication.main(new String[]{});
	}

}
