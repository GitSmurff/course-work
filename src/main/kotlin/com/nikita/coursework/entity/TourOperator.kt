package com.nikita.coursework.entity

import com.nikita.coursework.coursework.entity.base.TimedEntity
import javax.persistence.*

@Entity
@Table(name = "tour_operator")
class TourOperator(

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "agency_id")
    var agency: TourAgency,

    @Column(name = "first_name")
    var firstName: String,

    @Column(name = "last_name")
    var lastName: String,

    @Column(name = "middle_name")
    var middleName: String,

    @Column(name = "is_deleted")
    var isDeleted: Boolean

): TimedEntity()