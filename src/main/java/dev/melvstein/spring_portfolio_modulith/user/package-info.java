@ApplicationModule(
        allowedDependencies = {
                "auth::kafka-event",
                "common::vo"
        }
)
package dev.melvstein.spring_portfolio_modulith.user;

import org.springframework.modulith.ApplicationModule;