package com.tuimm.learningpath.authorization;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JWTDecoder {
    private final String jwtIssuer;
    private final String publicKey;

    public JWTDecoder(@Value("${security.jwt.issuer}") String jwtIssuer,
                      @Value("${security.jwt.public_key}") String publicKey) {
        this.jwtIssuer = jwtIssuer;
        this.publicKey = publicKey;
    }

    public DecodedJWT decodeJWT(String token) {
        JWTVerifier verifier = JWT.require(Algorithm.none())
                .build();
        return verifier.verify(token);
    }
}
