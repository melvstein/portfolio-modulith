@ApplicationModule(
        allowedDependencies = {
                "auth::kafka-event",
                "auth::dto",
                "auth::enm",
                "auth::facade",
                "common::vo",
                "common::enm",
                "common::kafka-event"
        }
)
package dev.melvstein.spring_portfolio_modulith.audit;

import org.springframework.modulith.ApplicationModule;