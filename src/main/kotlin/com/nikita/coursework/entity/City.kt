package com.nikita.coursework.coursework.entity

import com.nikita.coursework.coursework.entity.base.BaseEntity
import javax.persistence.*

@Entity
@Table(name = "city")
class City (

    @Column(name = "city_name")
    var name: String,

    @ManyToOne
    @JoinColumn(name = "state_id")
    var state: State
): BaseEntity()