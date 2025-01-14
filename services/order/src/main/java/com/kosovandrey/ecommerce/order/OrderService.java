package com.kosovandrey.ecommerce.order;

import com.kosovandrey.ecommerce.customer.CustomerClient;
import com.kosovandrey.ecommerce.exception.BusinessException;
import com.kosovandrey.ecommerce.kafka.OrderConfirmation;
import com.kosovandrey.ecommerce.kafka.OrderProducer;
import com.kosovandrey.ecommerce.orderline.OrderLineRequest;
import com.kosovandrey.ecommerce.orderline.OrderLineService;
import com.kosovandrey.ecommerce.payment.PaymentClient;
import com.kosovandrey.ecommerce.payment.PaymentRequest;
import com.kosovandrey.ecommerce.product.ProductClient;
import com.kosovandrey.ecommerce.product.PurchaseRequest;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Сервис для управления заказами.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderProducer orderProducer;
    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final PaymentClient paymentClient;
    private final OrderRepository orderRepository;
    private final OrderLineService orderLineService;
    private final OrderMapper mapper;

    /**
     * Создает новый заказ.
     *
     * @param request Запрос на создание заказа.
     * @return Идентификатор созданного заказа.
     * @throws BusinessException Если клиент не найден или произошла ошибка при обработке заказа.
     */
    public Integer createOrder(@Valid OrderRequest request) {
        log.info("Creating order for customer: {}", request.customerId());

        // Поиск клиента по ID
        var customer = this.customerClient.findCustomerById(request.customerId())
                .orElseThrow(() -> {
                    log.error("Customer not found with ID: {}", request.customerId());
                    return new BusinessException("Cannot create order: No Customer exists with the provided ID");
                });

        log.info("Customer found: {}", customer.email());

        // Обработка покупки товаров
        var purchasedProducts = this.productClient.purchaseProducts(request.products());
        log.info("Products purchased successfully: {}", purchasedProducts.size());

        // Сохранение заказа в базе данных
        var order = this.orderRepository.save(mapper.toOrder(request));
        log.info("Order created with ID: {}", order.getId());

        // Сохранение позиций заказа
        for (PurchaseRequest purchaseRequest : request.products()) {
            orderLineService.saveOrderLine(
                    new OrderLineRequest(
                            null,
                            order.getId(),
                            purchaseRequest.productId(),
                            purchaseRequest.quantity()
                    )
            );
        }
        log.info("Order lines saved for order ID: {}", order.getId());

        // Создание запроса на оплату
        var paymentRequest = new PaymentRequest(
                request.amount(),
                request.paymentMethod(),
                request.id(),
                request.reference(),
                customer
        );
        paymentClient.requestOrderPayment(paymentRequest);
        log.info("Payment request sent for order ID: {}", order.getId());

        // Отправка подтверждения заказа через Kafka
        orderProducer.sendOrderConfirmation(new OrderConfirmation(
                request.reference(),
                request.amount(),
                request.paymentMethod(),
                customer,
                purchasedProducts
        ));
        log.info("Order confirmation sent to Kafka for order ID: {}", order.getId());

        return order.getId();
    }

    /**
     * Возвращает список всех заказов.
     *
     * @return Список заказов.
     */
    public List<OrderResponse> findAll() {
        log.info("Fetching all orders");
        return orderRepository.findAll().stream().map(mapper::fromOrder).toList();
    }

    /**
     * Возвращает заказ по его идентификатору.
     *
     * @param orderId Идентификатор заказа.
     * @return Информация о заказе.
     * @throws EntityNotFoundException Если заказ не найден.
     */
    public OrderResponse findById(Integer orderId) {
        log.info("Fetching order by ID: {}", orderId);
        return orderRepository.findById(orderId).map(mapper::fromOrder)
                .orElseThrow(() -> {
                    log.error("Order not found with ID: {}", orderId);
                    return new EntityNotFoundException("No order found with the provided ID: " + orderId);
                });
    }
}
