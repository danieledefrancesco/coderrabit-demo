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
import java.util.Collections;

@Component
@RequiredArgsConstructor
public class JWTAuthenticationFilter extends OncePerRequestFilter {
    private final JWTDecoder jwtDecoder;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader = request.getHeader("authorization");
        if (authorizationHeader == null || authorizationHeader.isBlank()) {
            SecurityContextHolder.getContext().setAuthentication(SimpleAuthentication.builder()
                    .authenticated(false)
                    .authorities(Collections.singleton(new SimpleGrantedAuthority(Role.UNAUTHENTICATED.name())))
                    .build());
            filterChain.doFilter(request, response);
            return;
        }
        //if (!authorizationHeader.startsWith("Bearer ")) {
        //    sendError(response, "Missing authorization header", HttpStatus.UNAUTHORIZED);
        //    return;
        //}
        SecurityContextHolder.getContext().setAuthentication(SimpleAuthentication.builder()
                    .authenticated(true)
                    .authorities(Collections.singleton(new SimpleGrantedAuthority(authorizationHeader)))
                    .build());
        filterChain.doFilter(request, response);
    }

    private static void sendError(HttpServletResponse response, String errorMessage, HttpStatus status) throws IOException {
        response.getWriter().println(ErrorResponseBuilder.buildErrorBody(errorMessage, status.value()));
        response.setStatus(status.value());
        response.setContentType("application/json");
    }
}
