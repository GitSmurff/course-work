package com.nikita.coursework.coursework.entity.base

import org.springframework.data.annotation.LastModifiedDate
import java.time.ZonedDateTime
import javax.persistence.Column
import javax.persistence.MappedSuperclass
import javax.persistence.PrePersist
import javax.persistence.PreUpdate

@MappedSuperclass
open class TimedEntity : BaseEntity() {

    @Column(name = "created_at", updatable = false)
    open var createdAt: ZonedDateTime? = null

    @LastModifiedDate
    @Column(name = "changed_at")
    open var changedAt: ZonedDateTime? = null

    @PrePersist
    open fun prePersist() {
        if (this.createdAt == null) {
            createdAt = ZonedDateTime.now()
        }
    }

    @PreUpdate
    open fun preUpdate() {
        if (this.changedAt == null) {
            this.changedAt = ZonedDateTime.now()
        }
    }
}
