package com.tuimm.learningpath.authorization;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;

import java.util.function.Supplier;

@RequiredArgsConstructor(staticName = "forRole")
public class HierarchicalAuthorizationManager implements AuthorizationManager<RequestAuthorizationContext> {
    private final Role requiredRole;
    @Override
    public AuthorizationDecision check(Supplier<Authentication> authentication, RequestAuthorizationContext object) {
        return new AuthorizationDecision(authentication.get().getAuthorities()
                .stream()
                .anyMatch(authority -> isAuthorized(Role.valueOf(authority.getAuthority()))));
    }

    private boolean isAuthorized(Role currentUserRole) {
        if (currentUserRole == null) return false;
        if (currentUserRole == requiredRole) return true;
        return isAuthorized(currentUserRole.getParentRole());
    }
}
