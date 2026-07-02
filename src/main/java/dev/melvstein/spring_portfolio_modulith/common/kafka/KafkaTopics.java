package dev.melvstein.spring_portfolio_modulith.common.kafka;

public final class KafkaTopics {

    private KafkaTopics() {
    }

    /**
     * User Events
     */
    public static final String USER_REGISTERED = "portfolio.user.registered.v1";
    public static final String USER_UPDATED = "portfolio.user.updated.v1";

}
