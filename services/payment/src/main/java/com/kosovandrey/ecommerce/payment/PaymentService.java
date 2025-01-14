package com.kosovandrey.ecommerce.payment;

import com.kosovandrey.ecommerce.notification.NotificationProducer;
import com.kosovandrey.ecommerce.notification.PaymentNotificationRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Сервис для управления платежами.
 */
@RequiredArgsConstructor
@Slf4j
@Service
public class PaymentService {
    private final PaymentRepository repository;
    private final PaymentMapper mapper;
    private final NotificationProducer notificationProducer;

    /**
     * Создает новый платеж и отправляет уведомление.
     *
     * @param request Запрос на создание платежа.
     * @return Идентификатор созданного платежа.
     */
    public Integer create(PaymentRequest request) {
        log.info("Creating payment for order reference: {}", request.orderReference());

        // Сохранение платежа в базе данных
        var payment = repository.save(mapper.toPayment(request));
        log.info("Payment created with ID: {}", payment.getId());

        // Отправка уведомления о платеже
        notificationProducer.sendNotification(new PaymentNotificationRequest(
                request.orderReference(),
                request.amount(),
                request.paymentMethod(),
                request.customer().firstname(),
                request.customer().lastname(),
                request.customer().email()
        ));

        log.info("Notification sent for payment ID: {}", payment.getId());
        return payment.getId();
    }
}