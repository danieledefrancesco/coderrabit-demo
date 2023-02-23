package com.tuimm.learningpath.stepdefinitions.authorization;

import com.tuimm.learningpath.stepdefinitions.Definition;
import io.cucumber.java.en.Given;

public class AuthorizationSteps extends Definition {

    @Given("the client is authenticated as a {word}")
    public void theClientIsAuthenticatedAs(String role) {
        scenarioContext.getDriver().addHeader("authorization", role);
    }

    @Given("the client is not authenticated")
    public void theClientIsNotAuthenticated() {
        scenarioContext.getDriver().removeHeader("authorization");
    }

}
