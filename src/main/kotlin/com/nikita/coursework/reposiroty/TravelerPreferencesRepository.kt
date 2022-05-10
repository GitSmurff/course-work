package com.nikita.coursework.coursework.repository

import com.nikita.coursework.coursework.entity.TravelerPreferences
import com.nikita.coursework.coursework.repository.base.BaseRepository
import org.springframework.stereotype.Repository

@Repository
interface TravelerPreferencesRepository: BaseRepository<TravelerPreferences> {
}