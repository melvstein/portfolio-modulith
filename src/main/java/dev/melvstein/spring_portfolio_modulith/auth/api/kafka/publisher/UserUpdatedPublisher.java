package dev.melvstein.spring_portfolio_modulith.auth.api.kafka.publisher;

import dev.melvstein.spring_portfolio_modulith.common.api.kafka.event.AuditLogEvent;
import dev.melvstein.spring_portfolio_modulith.common.api.kafka.KafkaTopics;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class UserUpdatedPublisher {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public UserUpdatedPublisher(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publish(AuditLogEvent event) {
        log.info("Publishing UserUpdatedEvent {}", event);

        kafkaTemplate.send(
                KafkaTopics.USER_UPDATED,
                event.entityId().toString(),
                event
        );
    }
}
