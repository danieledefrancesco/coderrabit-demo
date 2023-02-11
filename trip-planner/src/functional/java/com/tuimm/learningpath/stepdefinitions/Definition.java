package com.tuimm.learningpath.stepdefinitions;

import com.tuimm.learningpath.ScenarioContext;
import com.tuimm.learningpath.SpringChallenge;
import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;


@CucumberContextConfiguration
@ContextConfiguration(classes = SpringChallenge.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class Definition {
    @Autowired
    protected ScenarioContext scenarioContext;

    @Test
    void placeholder() {
        Assertions.assertTrue(true);
    }
}