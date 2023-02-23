package com.tuimm.learningpath.stepdefinitions.authorization;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.tuimm.learningpath.stepdefinitions.Definition;
import io.cucumber.java.en.Given;
import org.springframework.beans.factory.annotation.Value;

public class AuthorizationSteps extends Definition {

    @Value("${security.jwt.issuer}")
    private String jwtIssuer;

    @Given("the client is authenticated as a {word}")
    public void theClientIsAuthenticatedAs(String role) {
        String jwt = JWT.create()
                .withClaim("role", role)
                .withIssuer(jwtIssuer)
                .sign(Algorithm.none());
        scenarioContext.getDriver().addHeader("authorization", String.format("Bearer %s", jwt));
    }

    @Given("the client is authenticated with an invalid issuer")
    public void theClientIsAuthenticatedWithInvalidIssuer() {
        String jwt = JWT.create()
                .withClaim("role", "role")
                .withIssuer("invalid-issuer")
                .sign(Algorithm.none());
        scenarioContext.getDriver().addHeader("authorization", String.format("Bearer %s", jwt));
    }

    @Given("the client is not authenticated")
    public void theClientIsNotAuthenticated() {
        scenarioContext.getDriver().removeHeader("authorization");
    }

}
