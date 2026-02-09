package com.nettside.crm.infrastructure.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * JPA Auditing configuration for automatic audit trail management.
 *
 * This configuration enables Spring Data JPA's auditing capabilities, which automatically
 * populate audit fields in entities that extend {@link com.nettside.crm.domain.common.entity.AuditableEntity}.
 *
 * Features enabled by this configuration:
 * - Automatic population of @CreatedDate fields on entity creation
 * - Automatic population of @LastModifiedDate fields on entity updates
 * - Automatic population of @CreatedBy fields using the configured AuditorAware bean
 * - Automatic population of @LastModifiedBy fields using the configured AuditorAware bean
 *
 * The auditor (current user) is provided by the {@link AuditorAwareImpl} bean,
 * which extracts the user ID from the Spring Security context.
 *
 * Configuration Details:
 * - auditorAwareRef: References the "auditorAwareImpl" bean for user ID resolution
 * - Date/Time handling: Uses LocalDateTime for timestamp fields
 * - Time zone: All timestamps are stored in UTC (configured in application.yml)
 *
 * Requirements:
 * - Spring Security must be configured for user authentication
 * - AuditorAwareImpl bean must be present in the application context
 * - Database must support the audit column types (BIGINT for user IDs, TIMESTAMP for dates)
 *
 * Thread Safety:
 * This configuration is thread-safe as JPA auditing uses ThreadLocal contexts
 * for both database operations and security principal access.
 *
 * Usage:
 * Entities that require auditing should extend AuditableEntity:
 * <pre>
 * {@code
 * @Entity
 * public class MyEntity extends AuditableEntity {
 *     // entity fields
 * }
 * }
 * </pre>
 *
 * The audit fields will be automatically populated on save/update operations.
 *
 * @author Alex Coronell
 * @version 1.0.0
 * @since 2026-02-07
 * @see EnableJpaAuditing
 * @see AuditorAwareImpl
 * @see com.nettside.crm.domain.common.entity.AuditableEntity
 */
@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorAwareImpl")
public class JpaAuditingConfig {
    // No additional beans required - configuration only
}