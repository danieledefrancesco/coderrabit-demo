package com.tuimm.learningpath;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import java.time.LocalDate;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/functional/resources/features",
        plugin = {"pretty", "html:target/cucumber/spring-challenge-specs.html"},
        dryRun = true,
        monochrome = true)
public class CucumberTest {

    @TestConfiguration
    public static class ServicesOverride {
        @Bean
        @Primary
        public TodayDateProvider todayDateProvider() {
            TodayDateProvider todayDateProvider = mock(TodayDateProvider.class);
            when(todayDateProvider.getTodayDate()).thenReturn(LocalDate.now());
            return todayDateProvider;
        }
    }
}
