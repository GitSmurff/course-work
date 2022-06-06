package com.nikita.coursework.entity

import com.nikita.coursework.coursework.entity.base.TimedEntity
import java.time.LocalDate
import javax.persistence.*

@Entity
@Table(name = "registration_airport")
class RegistrationInAirport(

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "traveler_id")
    var traveler: Traveler,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "airport_id")
    var airport: Airport,

    @Column(name = "arrived_date")
    var arrivedDate: LocalDate,

    @Column(name = "departed_date")
    var departedDate: LocalDate,

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    var status: RegistrationStatus,

    @Column(name = "is_deleted")
    var isDeleted: Boolean

): TimedEntity()

enum class RegistrationStatus {
    ARRIVED,
    DEPARTED
}