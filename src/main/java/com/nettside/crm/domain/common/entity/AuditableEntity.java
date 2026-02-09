package com.nettside.crm.domain.common.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

/**
 * Base auditable entity providing automatic audit trail capabilities.
 *
 * This abstract class automatically tracks entity creation and modification metadata
 * through JPA auditing annotations. It should be extended by all domain entities
 * that require audit trail functionality.
 *
 * Features:
 * - Automatic creation timestamp and user tracking
 * - Automatic modification timestamp and user tracking
 * - Integration with Spring Data JPA auditing
 *
 * Note: Requires {@link JpaAuditingConfig} to be enabled in the application context
 * for automatic auditing to function properly.
 *
 * @author Alex Coronell
 * @version 1.0.0
 * @since 2026-02-07
 * @see AuditingEntityListener
 * @see JpaAuditingConfig
 */
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@NoArgsConstructor
public abstract class AuditableEntity {

    /**
     * Timestamp when the entity was created.
     * Automatically populated by JPA auditing on entity creation.
     * This field is immutable after creation.
     */
    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    /**
     * ID of the user who created this entity.
     * Automatically populated by JPA auditing on entity creation.
     * This field is immutable after creation.
     */
    @CreatedBy
    @Column(name = "created_by", updatable = false)
    private Long createdBy;

    /**
     * Timestamp when the entity was last modified.
     * Automatically updated by JPA auditing on each entity modification.
     */
    @LastModifiedDate
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    /**
     * ID of the user who last modified this entity.
     * Automatically updated by JPA auditing on each entity modification.
     */
    @LastModifiedBy
    @Column(name = "updated_by")
    private Long updatedBy;
}