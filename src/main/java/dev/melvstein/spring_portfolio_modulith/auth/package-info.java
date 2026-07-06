@ApplicationModule(
        allowedDependencies = {
                "common::enm",
                "common::exception",
                "common::vo",
                "common::kafka-event",
                "common::utils",
                "audit::enm",
                "audit::kafka-event"
        }
)
package dev.melvstein.spring_portfolio_modulith.auth;

import org.springframework.modulith.ApplicationModule;