package dev.melvstein.spring_portfolio_modulith;

import org.junit.jupiter.api.Test;
import org.springframework.modulith.core.ApplicationModules;

public class ModularityTest {

    @Test
    void verifiesModularStructure() {
        ApplicationModules.of(SpringPortfolioModulithApplication.class).verify();
    }
}
