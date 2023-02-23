package com.tuimm.learningpath.authorization;

import com.tuimm.learningpath.exceptions.ErrorResponseBuilder;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;

@Component
@RequiredArgsConstructor
public class JWTAuthenticationFilter extends OncePerRequestFilter {
    private final JWTDecoder jwtDecoder;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader = request.getHeader("authorization");
        if (noAuthorizationHeaderIsProvided(authorizationHeader)) {
            setRoleAndContinueChain(false, Role.UNAUTHENTICATED.name(), filterChain, request, response);
            return;
        }
        if (authorizationHeaderContainsInvalidBearerToken(response, authorizationHeader)) return;
        String bearerToken = authorizationHeader.substring(7);
        String role = bearerToken;
        if (roleIsInvalid(response, role)) return;
        setRoleAndContinueChain(true, role, filterChain, request, response);
    }

    private static boolean roleIsInvalid(HttpServletResponse response, String roleName) throws IOException{
        if (Arrays.stream(Role.values()).noneMatch(role -> role.name().equals(roleName))) {
            sendError(response, "Unrecognized role.", HttpStatus.UNAUTHORIZED);
            return true;
        }
        return false;
    }

    private static boolean authorizationHeaderContainsInvalidBearerToken(HttpServletResponse response, String authorizationHeader) throws IOException {
        if (!authorizationHeader.startsWith("Bearer ")) {
            sendError(response, "The authorization header does not contain a valid bearer token.", HttpStatus.UNAUTHORIZED);
            return true;
        }
        return false;
    }

    private static void setRoleAndContinueChain(boolean authenticated, String roleName, FilterChain filterChain, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        SecurityContextHolder.getContext().setAuthentication(SimpleAuthentication.builder()
                .authenticated(authenticated)
                .authorities(Collections.singleton(new SimpleGrantedAuthority(roleName)))
                .build());
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
