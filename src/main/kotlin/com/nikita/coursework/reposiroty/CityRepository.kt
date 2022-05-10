package com.nikita.coursework.coursework.repository

import com.nikita.coursework.coursework.entity.City
import com.nikita.coursework.coursework.repository.base.BaseRepository
import org.springframework.stereotype.Repository

@Repository
interface CityRepository: BaseRepository<City> {
}