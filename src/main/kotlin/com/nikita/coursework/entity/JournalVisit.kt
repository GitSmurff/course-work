package com.nikita.coursework.coursework.entity

import com.nikita.coursework.coursework.entity.base.TimedEntity
import java.time.LocalDate
import javax.persistence.*

@Entity
@Table(name = "journal_visit")
class JournalVisit(

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "traveler_id")
    var traveler: Traveler,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "operator_id")
    var operator: TourOperator,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tour_id")
    var tour: Tour,

    @Column(name = "visit_date")
    var visitDate: LocalDate,

    @Column(name = "is_arrived")
    var isArrived: Boolean,

    @Column(name = "is_deleted")
    var isDeleted: Boolean

): TimedEntity()