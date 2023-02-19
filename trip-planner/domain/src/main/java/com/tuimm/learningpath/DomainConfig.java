package com.tuimm.learningpath;

import com.tuimm.learningpath.trips.StagePlan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Supplier;

@Configuration
public class DomainConfig {
    @Bean
    public Supplier<StagePlan.StagePlanBuilder> stagePlanBuilderSupplier() {
        return StagePlan::builder;
    }
}
