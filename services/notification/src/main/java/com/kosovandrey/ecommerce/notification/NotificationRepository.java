package com.kosovandrey.ecommerce.notification;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Репозиторий для работы с уведомлениями.
 */
public interface NotificationRepository extends MongoRepository<Notification, String> {
}