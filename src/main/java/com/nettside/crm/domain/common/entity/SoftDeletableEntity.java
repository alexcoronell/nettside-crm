package com.nettside.crm.domain.common.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SoftDelete;

import java.time.LocalDateTime;

/**
 * Base entity providing soft delete capabilities with audit trail.
 *
 * Extends {@link AuditableEntity} to include soft delete functionality,
 * allowing entities to be marked as deleted without physical removal from the database.
 * This approach preserves historical data and maintains referential integrity.
 *
 * The {@code @SoftDelete} annotation from Hibernate enables automatic filtering
 * of deleted records in queries unless explicitly requested.
 *
 * Features:
 * - Soft delete support (logical deletion)
 * - Tracks who and when deleted the entity
 * - Inherits all auditing capabilities from AuditableEntity
 * - Automatic query filtering of deleted records
 *
 * Usage:
 * <pre>
 * {@code
 * @Entity
 * public class MyEntity extends SoftDeletableEntity {
 *     // entity fields
 * }
 *
 * // To soft delete an entity:
 * myEntity.markDeleted(currentUserId);
 * repository.save(myEntity);
 * }
 * </pre>
 *
 * @author Alex Coronell
 * @version 1.0.0
 * @since 2026-02-07
 * @see AuditableEntity
 * @see SoftDelete
 */
@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
@SoftDelete
public abstract class SoftDeletableEntity extends AuditableEntity {

    /**
     * ID of the user who deleted this entity.
     * Null if the entity has not been deleted.
     */
    @Column(name = "deleted_by")
    private Long deletedBy;

    /**
     * Timestamp when the entity was deleted.
     * Null if the entity has not been deleted.
     */
    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    /**
     * Marks this entity as deleted by setting the deletion timestamp and user ID.
     *
     * This method performs a soft delete by recording when and by whom the entity
     * was deleted, without physically removing it from the database.
     * The entity must be saved after calling this method for changes to persist.
     *
     * Note: This does not automatically persist the changes. The entity must be
     * saved through the repository after calling this method.
     *
     * @param userId the ID of the user performing the delete operation
     * @throws IllegalArgumentException if userId is null
     */
    public void markDeleted(Long userId) {
        if (userId == null) {
            throw new IllegalArgumentException("User ID cannot be null when marking entity as deleted");
        }
        this.deletedAt = LocalDateTime.now();
        this.deletedBy = userId;
    }

    /**
     * Checks if this entity has been soft deleted.
     *
     * @return true if the entity has been deleted, false otherwise
     */
    public boolean isDeleted() {
        return deletedAt != null;
    }

    /**
     * Restores a soft deleted entity by clearing deletion metadata.
     *
     * This method can be used to "undelete" an entity by removing the
     * deletion timestamp and user ID. The entity must be saved after
     * calling this method for changes to persist.
     *
     * Note: This does not automatically persist the changes. The entity must be
     * saved through the repository after calling this method.
     */
    public void restore() {
        this.deletedAt = null;
        this.deletedBy = null;
    }
}