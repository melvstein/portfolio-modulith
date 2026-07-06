package dev.melvstein.spring_portfolio_modulith.user.kafka;

import dev.melvstein.spring_portfolio_modulith.common.api.kafka.KafkaGroups;
import dev.melvstein.spring_portfolio_modulith.common.api.kafka.KafkaTopics;
import dev.melvstein.spring_portfolio_modulith.auth.api.kafka.event.UserRegisteredEvent;
import dev.melvstein.spring_portfolio_modulith.user.internal.service.ProfileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@Slf4j
public class UserRegisteredListener {

    private final ProfileService profileService;

    @Transactional
    @KafkaListener(
            topics = KafkaTopics.USER_REGISTERED,
            groupId = KafkaGroups.USERS
    )
    public void handleRegister(UserRegisteredEvent event) {
        log.info("Received UserRegisteredEvent {}", event);

        profileService.createProfile(event);
    }
}
