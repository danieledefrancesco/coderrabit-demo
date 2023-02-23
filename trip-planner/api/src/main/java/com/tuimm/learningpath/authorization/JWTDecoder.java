package com.tuimm.learningpath.authorization;

import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Component;

@Component
public class JWTDecoder {
    public DecodedJWT decodedJWT(String token) {
        return null;
    }
}
