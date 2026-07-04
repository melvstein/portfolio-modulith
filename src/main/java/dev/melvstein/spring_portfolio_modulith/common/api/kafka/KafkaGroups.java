package dev.melvstein.spring_portfolio_modulith.common.api.kafka;

public final class KafkaGroups {

    private KafkaGroups() {
    }

    /**
     * Internal Consumers
     */
    public static final String USERS = "users-group";
    public static final String AUDIT = "audit-group";
}
