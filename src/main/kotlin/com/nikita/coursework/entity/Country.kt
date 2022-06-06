package com.nikita.coursework.entity

import com.nikita.coursework.coursework.entity.base.BaseEntity
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "country")
class Country(

    @Column(name = "country_name")
    var name: String,

    @Column(name = "code")
    var code: String
): BaseEntity()
