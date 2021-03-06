package com.nikita.coursework.entity

import com.nikita.coursework.coursework.entity.base.TimedEntity
import javax.persistence.*

@Entity
@Table(name = "tour_agency")
class TourAgency (

    @Column(name = "agency_name")
    var name: String,

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Location::class)
    @JoinColumn(name = "location_id")
    var location: Location,

    @Column(name = "is_deleted")
    var isDeleted: Boolean
): TimedEntity()