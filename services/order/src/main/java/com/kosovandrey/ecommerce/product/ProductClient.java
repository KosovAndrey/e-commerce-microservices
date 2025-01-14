package com.kosovandrey.ecommerce.product;

import com.kosovandrey.ecommerce.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Клиент для взаимодействия с микросервисом product.
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class ProductClient {

    @Value("${application.config.product-url}")
    private String productUrl;
    private final RestTemplate restTemplate;

    /**
     * Отправляет запрос на покупку товаров.
     *
     * @param requestBody Список товаров для покупки.
     * @return Список купленных товаров.
     * @throws BusinessException Если произошла ошибка при обработке запроса.
     */
    public List<PurchaseResponse> purchaseProducts(List<PurchaseRequest> requestBody) {
        log.info("Sending purchase request for {} products", requestBody.size());
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<List<PurchaseRequest>> requestEntity = new HttpEntity<>(requestBody, headers);
        ParameterizedTypeReference<List<PurchaseResponse>> responseType = new ParameterizedTypeReference<>() {
        };
        ResponseEntity<List<PurchaseResponse>> responseEntity = restTemplate.exchange(
                productUrl + "/purchase", HttpMethod.POST, requestEntity, responseType
        );
        if (responseEntity.getStatusCode().isError()) {
            log.error("Failed to purchase products: {}", responseEntity.getStatusCode());
            throw new BusinessException("An error occurred while processing the products purchase " + responseEntity.getStatusCode());
        }
        log.info("Products purchased successfully");
        return responseEntity.getBody();
    }
}