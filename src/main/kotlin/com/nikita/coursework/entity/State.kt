package com.nikita.coursework.coursework.entity

import com.nikita.coursework.coursework.entity.base.BaseEntity
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "state")
class State(
    @Column(name = "state_name")
    var name: String,

    @Column(name = "code")
    var code: String

): BaseEntity()
