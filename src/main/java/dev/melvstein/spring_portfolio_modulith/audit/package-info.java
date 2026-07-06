@ApplicationModule(
        allowedDependencies = {
                "auth::kafka-event",
                "auth::dto",
                "auth::enm",
                "auth::facade",
                "common::vo"
        }
)
package dev.melvstein.spring_portfolio_modulith.audit;

import org.springframework.modulith.ApplicationModule;