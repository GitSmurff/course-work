package com.nikita.coursework.entity

import com.nikita.coursework.coursework.entity.base.BaseEntity
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "region")
class Region(

    @Column(name = "title")
    var title: String,

    @Column(name = "description")
    var description: String?
): BaseEntity()