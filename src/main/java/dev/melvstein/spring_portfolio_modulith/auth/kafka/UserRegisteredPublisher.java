package dev.melvstein.spring_portfolio_modulith.auth.kafka;

import dev.melvstein.spring_portfolio_modulith.common.kafka.KafkaTopics;
import dev.melvstein.spring_portfolio_modulith.common.kafka.event.UserRegisteredEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class UserRegisteredPublisher {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void publish(UserRegisteredEvent event) {
        log.info("Publishing UserRegisteredEvent {}", event);

        kafkaTemplate.send(
                KafkaTopics.USER_REGISTERED,
                event.authUserId().toString(),
                event
        );
    }
}
