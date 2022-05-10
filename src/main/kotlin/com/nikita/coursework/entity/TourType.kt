package com.nikita.coursework.coursework.entity

import com.nikita.coursework.coursework.entity.base.BaseEntity
import javax.persistence.*

@Entity
@Table(name = "tour_type")
class TourType(

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "agency_id")
    var agency: TourAgency,

    @Column(name = "title")
    var title: String

): BaseEntity()