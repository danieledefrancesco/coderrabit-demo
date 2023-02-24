package com.tuimm.learningpath.stepdefinitions.authorization;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.tuimm.learningpath.authorization.RSAPublicKeySupplier;
import com.tuimm.learningpath.stepdefinitions.Definition;
import io.cucumber.java.en.Given;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.security.interfaces.RSAPrivateKey;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.function.Function;

public class AuthorizationSteps extends Definition {

    @Value("${security.jwt.issuer}")
    private String jwtIssuer;
    @Autowired
    private RSAPublicKeySupplier publicKeySupplier;
    @Autowired
    private Function<String, RSAPrivateKey> privateKeySupplier;

    @Given("the client is authenticated as a {word}")
    public void theClientIsAuthenticatedAs(String role) {
        String jwt = JWT.create()
                .withClaim("role", role)
                .withIssuer(jwtIssuer)
                .withExpiresAt(LocalDateTime.now().plusHours(1).toInstant(ZoneOffset.UTC))
                .sign(Algorithm.RSA512(publicKeySupplier.get(), privateKeySupplier.apply("keys/jwt_private.key")));
        scenarioContext.getDriver().addHeader("authorization", String.format("Bearer %s", jwt));
    }

    @Given("the client authentication has expired")
    public void theClientAuthenticationHasExpired() {
        String jwt = JWT.create()
                .withClaim("role", "role")
                .withIssuer(jwtIssuer)
                .withExpiresAt(LocalDateTime.now().minusHours(1).toInstant(ZoneOffset.UTC))
                .sign(Algorithm.RSA512(publicKeySupplier.get(), privateKeySupplier.apply("keys/jwt_private.key")));
        scenarioContext.getDriver().addHeader("authorization", String.format("Bearer %s", jwt));
    }

    @Given("the client is authenticated with an invalid issuer")
    public void theClientIsAuthenticatedWithInvalidIssuer() {
        String jwt = JWT.create()
                .withClaim("role", "role")
                .withIssuer("invalid-issuer")
                .withExpiresAt(LocalDateTime.now().plusHours(1).toInstant(ZoneOffset.UTC))
                .sign(Algorithm.RSA512(publicKeySupplier.get(), privateKeySupplier.apply("keys/jwt_private.key")));
        scenarioContext.getDriver().addHeader("authorization", String.format("Bearer %s", jwt));
    }

    @Given("the client is authenticated with a token signed with a different key")
    public void theClientIsAuthenticatedWithInvalidSignature() {
        String jwt = JWT.create()
                .withClaim("role", "role")
                .withIssuer(jwtIssuer)
                .withExpiresAt(LocalDateTime.now().plusHours(1).toInstant(ZoneOffset.UTC))
                .sign(Algorithm.RSA512(publicKeySupplier.get(), privateKeySupplier.apply("keys/jwt_invalid_private.key")));
        scenarioContext.getDriver().addHeader("authorization", String.format("Bearer %s", jwt));
    }

    @Given("the client is not authenticated")
    public void theClientIsNotAuthenticated() {
        scenarioContext.getDriver().removeHeader("authorization");
    }

}
