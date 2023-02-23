package com.tuimm.learningpath.authorization;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.tuimm.learningpath.exceptions.ErrorResponseBuilder;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
@RequiredArgsConstructor
public class JWTAuthenticationFilter extends OncePerRequestFilter {
    private final JWTDecoder jwtDecoder;

    @Override
    protected void doFilterInternal(HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {
        try {
            String authorizationHeader = request.getHeader("authorization");
            if (noAuthorizationHeaderIsProvided(authorizationHeader)) {
                setRoleAndContinueChain(Role.UNAUTHENTICATED.name(), filterChain, request, response);
                return;
            }
            if (authorizationHeaderContainsInvalidBearerToken(response, authorizationHeader)) return;
            String bearerToken = authorizationHeader.substring(7);
            DecodedJWT decodedJWT = jwtDecoder.decodeJWT(bearerToken);
            SimpleAuthentication authentication = SimpleAuthentication.fromJWT(decodedJWT);
            setAuthenticationAndContinueChain(authentication, filterChain, request, response);
        }
        catch (UnrecognizedRoleException e) {
            sendError(response, e.getMessage(), HttpStatus.UNAUTHORIZED);
        } catch (JWTVerificationException e) {
            sendError(response, "Invalid JWT.", HttpStatus.UNAUTHORIZED);
        }
    }

    private static boolean authorizationHeaderContainsInvalidBearerToken(HttpServletResponse response, String authorizationHeader) throws IOException {
        if (!authorizationHeader.startsWith("Bearer ")) {
            sendError(response, "The authorization header does not contain a valid bearer token.", HttpStatus.UNAUTHORIZED);
            return true;
        }
        return false;
    }

    private static void setRoleAndContinueChain(String roleName, FilterChain filterChain, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Authentication authentication = SimpleAuthentication.builder()
                .authenticated(false)
                .authorities(Collections.singleton(new SimpleGrantedAuthority(roleName)))
                .build();
        setAuthenticationAndContinueChain(authentication, filterChain, request, response);
    }

    private static void setAuthenticationAndContinueChain(Authentication authentication, FilterChain filterChain, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request, response);
    }

    private static boolean noAuthorizationHeaderIsProvided(String authorizationHeader) {
        return authorizationHeader == null || authorizationHeader.isBlank();
    }

    private static void sendError(HttpServletResponse response, String errorMessage, HttpStatus status) throws IOException {
        response.getWriter().println(ErrorResponseBuilder.buildErrorBody(errorMessage, status.value()));
        response.setStatus(status.value());
        response.setContentType("application/json");
    }
}
