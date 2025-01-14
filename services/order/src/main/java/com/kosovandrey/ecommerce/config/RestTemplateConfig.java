package com.kosovandrey.ecommerce.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Конфигурация для создания бина RestTemplate.
 */
@Configuration
public class RestTemplateConfig {

    /**
     * Создает и возвращает бин RestTemplate.
     *
     * @return Новый экземпляр RestTemplate.
     */
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}