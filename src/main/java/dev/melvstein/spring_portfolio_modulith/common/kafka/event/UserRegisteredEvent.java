package dev.melvstein.spring_portfolio_modulith.common.kafka.event;

import lombok.Builder;

import java.util.UUID;

@Builder
public record UserRegisteredEvent(

        UUID authUserId
) {
}
