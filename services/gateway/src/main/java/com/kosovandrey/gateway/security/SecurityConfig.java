package com.kosovandrey.gateway.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

/**
 * Конфигурация безопасности для API Gateway.
 * <p>
 * Этот класс настраивает безопасность для приложения, используя Spring Security WebFlux.
 * Он отключает CSRF защиту, разрешает доступ к эндпоинтам Eureka без аутентификации
 * и требует аутентификации для всех остальных запросов через OAuth2 Resource Server с JWT.
 * </p>
 */
@Slf4j
@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    /**
     * Настраивает цепочку фильтров безопасности для приложения.
     *
     * @param serverHttpSecurity Объект для настройки безопасности.
     * @return Настроенная цепочка фильтров безопасности.
     */
    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity serverHttpSecurity) {
        log.info("Configuring security filter chain...");

        serverHttpSecurity
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .authorizeExchange(exchange -> exchange
                        .pathMatchers("/eureka/**")
                        .permitAll()
                        .anyExchange()
                        .authenticated()
                )
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()));

        log.info("Security filter chain configured successfully.");
        return serverHttpSecurity.build();
    }
}