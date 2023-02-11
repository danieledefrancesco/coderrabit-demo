package com.tuimm.learningpath.stepdefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;

public class GenericHttpStepsDefinition extends Definition {
    @When("making a GET request to the {string} endpoint")
    public void makingAGetRequestToTheEndpoint(String path) {

        scenarioContext.getDriver().executeGet(path);
    }

    @When("making a POST request to the {string} endpoint")
    public void makingAPostRequestToTheEndpoint(String path) {
        scenarioContext.getDriver().executePost(path);
    }
    
    @Then("the status code should be {int}")
    public void theStatusCodeShouldBe(int expectedStatusCode) {
        Assertions.assertEquals(expectedStatusCode, scenarioContext.getDriver().getLastResponse().statusCode());
    }
}
