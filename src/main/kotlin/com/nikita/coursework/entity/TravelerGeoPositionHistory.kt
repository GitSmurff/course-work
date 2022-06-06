package com.nikita.coursework.entity

import com.nikita.coursework.coursework.entity.base.TimedEntity
import java.math.BigDecimal
import javax.persistence.*

@Entity
@Table(name = "traveler_geo_position_history")
class TravelerGeoPositionHistory(

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "traveler_id")
    var traveler: Traveler,

    @Column(name = "lat")
    var lat: BigDecimal,

    @Column(name = "lng")
    var lng: BigDecimal,

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    var status: TravelerGeoPositionStatusType
): TimedEntity()

enum class TravelerGeoPositionStatusType {
    OK, DELETE
}