package com.nikita.coursework.entity

import com.nikita.coursework.coursework.entity.base.TimedEntity
import javax.persistence.*

@Entity
@Table(name = "traveler")
class Traveler(

    @Column(name = "first_name")
    var firstName: String,

    @Column(name = "last_name")
    var lastName: String,

    @Column(name = "middle_name")
    var middleName: String,

    @Column(name = "phone_number")
    var phoneNumber: String?,

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Location::class)
    @JoinColumn(name = "location_id")
    var location: Location,

    @Column(name = "is_deleted")
    var isDeleted: Boolean
): TimedEntity()