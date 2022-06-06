package com.nikita.coursework.entity

import com.nikita.coursework.coursework.entity.base.BaseEntity
import java.math.BigDecimal
import javax.persistence.*

@Entity
@Table(name = "location")
class Location(

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id")
    var city: City,

    @Column(name = "street")
    var street: String?,

    @Column(name = "lat")
    var lat: BigDecimal,

    @Column(name = "lng")
    var lng: BigDecimal,

    @Column(name = "source")
    @Enumerated(EnumType.STRING)
    var source: LocationSourceType
): BaseEntity()
enum class LocationSourceType {
    AIRPORT,
    TRAVELER,
    AGENCY,
    TOUR
}
