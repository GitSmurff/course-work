package com.nikita.coursework.coursework.entity

import com.nikita.coursework.coursework.entity.base.BaseEntity
import java.math.BigDecimal
import javax.persistence.*

@Entity
@Table(name = "location")
class Location(

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id")
    var city: City,

    @Column(name = "city_name")
    var cityName: String,

    @Column(name = "state_short_code")
    var stateShortCode: String?,

    @Column(name = "street")
    var street: String,

    @Column(name = "zip_code")
    var zipCode: String,

    @Column(name = "lat")
    var lat: BigDecimal?,

    @Column(name = "lng")
    var lng: BigDecimal?

): BaseEntity()
