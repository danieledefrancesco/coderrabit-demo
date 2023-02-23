package com.tuimm.learningpath.authorization;

import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.io.Serial;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

@Getter
@Setter
@Builder
public class SimpleAuthentication implements Authentication {
    @Serial
    private static final long serialVersionUID = 1234567L;

    public static SimpleAuthentication fromJWT(DecodedJWT jwt) {
        String roleName = ensureValidRole(jwt.getClaim("role").asString());
        return builder()
                .authenticated(true)
                .authorities(Collections.singleton(new SimpleGrantedAuthority(roleName)))
                .build();
    }

    private static String ensureValidRole(String roleName) {
        if(Arrays.stream(Role.values()).noneMatch(role -> role.name().equals(roleName))) {
            throw new UnrecognizedRoleException("Unrecognized role.");
        }
        return roleName;
    }

    private final Collection<? extends GrantedAuthority> authorities;
    private final String name;
    private final Credentials credentials;
    private final UserDetails details;
    private final Principal principal;
    private boolean authenticated;

    public static class Credentials implements Serializable { }
    public static class UserDetails implements Serializable { }
    public static class Principal implements Serializable { }
}
