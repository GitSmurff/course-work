package com.nikita.coursework.entity

import com.nikita.coursework.coursework.entity.base.BaseEntity
import javax.persistence.*

@Entity
@Table(name = "city")
class City (

    @Column(name = "city_name")
    var name: String,

    @ManyToOne
    @JoinColumn(name = "country_id")
    var country: Country,

    @ManyToOne
    @JoinColumn(name = "region_id")
    var region: Region
): BaseEntity()