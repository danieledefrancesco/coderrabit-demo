package com.tuimm.learningpath;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/functional/resources/features",
        plugin = {"pretty", "html:target/cucumber/spring-challenge-specs.html"},
        dryRun = true,
        monochrome = true)
public class CucumberTest {
}
