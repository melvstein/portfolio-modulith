@ApplicationModule(
        allowedDependencies = {
                "auth::kafka-event",
                "auth::dto",
                "auth::enm",
                "auth::facade",
                "common::vo",
                "common::kafka-event",
        }
)
package dev.melvstein.spring_portfolio_modulith.user;

import org.springframework.modulith.ApplicationModule;