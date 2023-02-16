package com.tuimm.learningpath.stepdefinitions.exceptions;

import com.fasterxml.jackson.databind.JsonNode;
import com.tuimm.learningpath.stepdefinitions.Definition;
import io.cucumber.java.en.Then;
import org.junit.jupiter.api.Assertions;

public class ExceptionsStepsDefinition extends Definition {
    @Then("the error message should be {string}")
    public void theErrorMessageShouldBe(String message) {
        JsonNode response = scenarioContext.getDriver().getLastResponseAs(JsonNode.class);
        Assertions.assertEquals(message, response.get("error").textValue());
    }

    @Then("the error status should be {int}")
    public void theErrorStatusShouldBe(int code) {
        JsonNode response = scenarioContext.getDriver().getLastResponseAs(JsonNode.class);
        Assertions.assertEquals(code, response.get("status").asInt());
    }
}
