package com.nettside.crm.infrastructure.common.config;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Implementation of {@link AuditorAware} to provide current user information for JPA auditing.
 *
 * This component is responsible for extracting the current authenticated user's ID
 * from the Spring Security context. It is used by JPA auditing to automatically
 * populate {@code @CreatedBy} and {@code @LastModifiedBy} fields in auditable entities.
 *
 * The implementation attempts to extract the user ID from the Authentication object
 * in the following order:
 * 1. From the principal as a String (if it represents a user ID)
 * 2. From the authentication name
 *
 * If no authenticated user is found or the user is anonymous, an empty Optional is returned.
 *
 * Configuration:
 * This bean is referenced in {@link JpaAuditingConfig} via the name "auditorAwareImpl".
 *
 * Usage:
 * This component is used automatically by Spring Data JPA when saving entities
 * that extend {@link com.nettside.crm.domain.common.entity.AuditableEntity}.
 *
 * Thread Safety:
 * This implementation is thread-safe as it relies on Spring Security's
 * {@link SecurityContextHolder} which maintains a ThreadLocal context.
 *
 * @author Alex Coronell
 * @version 1.0.0
 * @since 2026-02-07
 * @see AuditorAware
 * @see JpaAuditingConfig
 * @see SecurityContextHolder
 */
@Component("auditorAwareImpl")
public class AuditorAwareImpl implements AuditorAware<Long> {

    /**
     * Returns the current authenticated user's ID.
     *
     * This method is called by JPA auditing when persisting or updating entities
     * to populate audit fields (@CreatedBy and @LastModifiedBy).
     *
     * The method attempts to extract the user ID from the Spring Security context:
     * - Returns empty if no authentication is present
     * - Returns empty if the user is not authenticated
     * - Returns empty if the user is anonymous
     * - Attempts to parse the principal or authentication name as a Long user ID
     *
     * Implementation Notes:
     * - The principal is expected to be either the user ID as a String or a custom UserDetails
     * - For custom UserDetails implementations, override this method accordingly
     * - NumberFormatException is caught and results in empty Optional for non-numeric IDs
     *
     * @return Optional containing the current user's ID, or empty if no authenticated user exists
     */
    @Override
    public Optional<Long> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // No authentication present
        if (authentication == null || !authentication.isAuthenticated()) {
            return Optional.empty();
        }

        // Anonymous user (not authenticated)
        if ("anonymousUser".equals(authentication.getPrincipal())) {
            return Optional.empty();
        }

        try {
            Object principal = authentication.getPrincipal();

            // Principal is directly the user ID as String
            if (principal instanceof String) {
                return Optional.of(Long.parseLong((String) principal));
            }

            // Fallback: try to get user ID from authentication name
            String name = authentication.getName();
            if (name != null && !name.isEmpty()) {
                return Optional.of(Long.parseLong(name));
            }

            // No valid user ID found
            return Optional.empty();

        } catch (NumberFormatException e) {
            // Principal or name is not a valid Long ID
            // This may occur if using username strings instead of user IDs
            return Optional.empty();
        }
    }
}