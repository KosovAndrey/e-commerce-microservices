package com.kosovandrey.ecommerce.email;

import com.kosovandrey.ecommerce.kafka.order.Product;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Сервис для отправки электронных писем.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;
    private final SpringTemplateEngine templateEngine;

    /**
     * Отправляет письмо об успешной оплате.
     *
     * @param destinationEmail Адрес электронной почты получателя.
     * @param customerName     Имя клиента.
     * @param amount           Сумма оплаты.
     * @param orderReference   Номер заказа.
     */
    @Async
    public void sendPaymentSuccessEmail(
            String destinationEmail,
            String customerName,
            BigDecimal amount,
            String orderReference
    ) {
        try {
            // Создание MIME-сообщения
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(
                    mimeMessage,
                    MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                    StandardCharsets.UTF_8.name()
            );

            // Настройка отправителя и получателя
            messageHelper.setFrom("contact@kosovandrey.ru");
            messageHelper.setTo(destinationEmail);

            // Загрузка шаблона письма
            final String templateName = EmailTemplates.PAYMENT_CONFIRMATION.getTemplate();
            Map<String, Object> variables = new HashMap<>();
            variables.put("customerName", customerName);
            variables.put("amount", amount);
            variables.put("orderReference", orderReference);

            // Генерация HTML-содержимого письма
            Context context = new Context();
            context.setVariables(variables);
            String htmlTemplate = templateEngine.process(templateName, context);

            // Настройка темы и содержимого письма
            messageHelper.setSubject(EmailTemplates.PAYMENT_CONFIRMATION.getSubject());
            messageHelper.setText(htmlTemplate, true);

            // Отправка письма
            mailSender.send(mimeMessage);
            log.info("Email successfully sent to {} with template {}", destinationEmail, templateName);
        } catch (MessagingException e) {
            log.error("Cannot send email to {} with template {}",
                    destinationEmail, EmailTemplates.PAYMENT_CONFIRMATION.getTemplate(), e);
            throw new RuntimeException("Failed to send email", e);
        }
    }

    /**
     * Отправляет письмо с подтверждением заказа.
     *
     * @param destinationEmail Адрес электронной почты получателя.
     * @param customerName     Имя клиента.
     * @param amount           Сумма заказа.
     * @param orderReference   Номер заказа.
     * @param products         Список товаров в заказе.
     */
    @Async
    public void sendOrderConfirmationEmail(
            String destinationEmail,
            String customerName,
            BigDecimal amount,
            String orderReference,
            List<Product> products
    ) {
        try {
            // Создание MIME-сообщения
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(
                    mimeMessage,
                    MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                    StandardCharsets.UTF_8.name()
            );

            // Настройка отправителя и получателя
            messageHelper.setFrom("contact@kosovandrey.ru");
            messageHelper.setTo(destinationEmail);

            // Загрузка шаблона письма
            final String templateName = EmailTemplates.ORDER_CONFIRMATION.getTemplate();
            Map<String, Object> variables = new HashMap<>();
            variables.put("customerName", customerName);
            variables.put("amount", amount);
            variables.put("orderReference", orderReference);
            variables.put("products", products);

            // Генерация HTML-содержимого письма
            Context context = new Context();
            context.setVariables(variables);
            String htmlTemplate = templateEngine.process(templateName, context);

            // Настройка темы и содержимого письма
            messageHelper.setSubject(EmailTemplates.ORDER_CONFIRMATION.getSubject());
            messageHelper.setText(htmlTemplate, true);

            // Отправка письма
            mailSender.send(mimeMessage);
            log.info("Email successfully sent to {} with template {}", destinationEmail, templateName);
        } catch (MessagingException e) {
            log.error("Cannot send email to {} with template {}",
                    destinationEmail, EmailTemplates.ORDER_CONFIRMATION.getTemplate(), e);
            throw new RuntimeException("Failed to send email", e);
        }
    }
}