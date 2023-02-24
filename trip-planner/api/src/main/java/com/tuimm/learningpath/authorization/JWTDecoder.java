package com.tuimm.learningpath.authorization;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.interfaces.RSAPublicKey;
import java.util.function.Supplier;

@Component
public class JWTDecoder {
    private final String jwtIssuer;
    private final Supplier<RSAPublicKey> rsaPublicKeySupplier;

    public JWTDecoder(@Value("${security.jwt.issuer}") String jwtIssuer,
                      Supplier<RSAPublicKey> rsaPublicKeySupplier) {
        this.jwtIssuer = jwtIssuer;
        this.rsaPublicKeySupplier = rsaPublicKeySupplier;
    }

    public DecodedJWT decodeJWT(String token) {
        RSAPublicKey publicKey = rsaPublicKeySupplier.get();
        JWTVerifier verifier = JWT.require(Algorithm.RSA512(publicKey))
                .withIssuer(jwtIssuer)
                .build();
        return verifier.verify(token);
    }
}
