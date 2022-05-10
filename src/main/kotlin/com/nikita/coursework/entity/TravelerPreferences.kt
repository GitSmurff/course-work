package com.nikita.coursework.coursework.entity

import com.nikita.coursework.coursework.entity.base.BaseEntity
import javax.persistence.*

@Entity
@Table(name = "traveler_preferences")
class TravelerPreferences(

    @ManyToOne
    @JoinColumn(name = "traveler_id")
    var traveler: Traveler,

    @Column(name = "title")
    var title: String,

    @Column(name = "description")
    var description: String?

): BaseEntity()