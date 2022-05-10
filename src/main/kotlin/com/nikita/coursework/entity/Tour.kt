package com.nikita.coursework.coursework.entity

import com.nikita.coursework.coursework.entity.base.TimedEntity
import java.math.BigDecimal
import java.time.LocalDate
import javax.persistence.*

@Entity
@Table(name = "tour")
class Tour(

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "traveler_id")
    var traveler: Traveler,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "agency_id")
    var agency: TourAgency,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id")
    var location: Location,

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tour_type_id")
    var tourType: TourType,

    @Column(name = "tour_name")
    var name: String,

    @Column(name = "description")
    var description: String?,

    @Column(name = "price")
    var price: BigDecimal,

    @Column(name = "is_deleted")
    var isDeleted: Boolean,

    @Column(name = "start_date")
    var startDate: LocalDate,

    @Column(name = "end_date")
    var endDate: LocalDate

): TimedEntity()