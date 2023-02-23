package com.tuimm.learningpath.stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;

public class GenericHttpStepsDefinition extends Definition {
    @Given("the {word} header is {string}")
    public void theHeaderIs(String headerName, String headerValue) {
        scenarioContext.getDriver().addHeader(headerName, headerValue);
    }
    @When("making a GET request to the {string} endpoint")
    public void makingAGetRequestToTheEndpoint(String path) {
        scenarioContext.getDriver().executeGet(path);
    }

    @When("making a POST request to the {string} endpoint")
    public void makingAPostRequestToTheEndpoint(String path) {

        scenarioContext.getDriver().executePost(path);
    }

    @When("making a PATCH request to the {string} endpoint")
    public void makingAPatchRequestToTheEndpoint(String path) {

        scenarioContext.getDriver().executePatch(path);
    }

    @When("making a DELETE request to the {string} endpoint")
    public void makingADeleteRequestToTheEndpoint(String path) {
        scenarioContext.getDriver().executeDelete(path);
    }

    @Then("the status code should be {int}")
    public void theStatusCodeShouldBe(int expectedStatusCode) {
        Assertions.assertEquals(expectedStatusCode,
                scenarioContext.getDriver().getLastResponse().statusCode(),
                String.format("The reponse body is: %s", scenarioContext.getDriver().getLastResponse().body()));
    }
}
