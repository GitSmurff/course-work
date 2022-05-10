package com.nikita.coursework.coursework.service

import com.nikita.coursework.coursework.entity.Traveler
import com.nikita.coursework.coursework.entity.TravelerPreferences
import com.nikita.coursework.coursework.repository.TravelerPreferencesRepository
import org.springframework.data.crossstore.ChangeSetPersister
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

interface TravelerPreferencesService {
    fun getById(id: Long): TravelerPreferences
    fun create(createRequest: TravelerPreferencesEntityCreateRequest): TravelerPreferences
    fun delete(preference: TravelerPreferences)
}

@Service
class TravelerPreferencesServiceImpl(
    private val repository: TravelerPreferencesRepository
):TravelerPreferencesService {
    @Transactional(readOnly = true)
    override fun getById(id: Long): TravelerPreferences {
        return repository.findById(id).orElseThrow { throw ChangeSetPersister.NotFoundException() }
    }

    @Transactional
    override fun create(createRequest: TravelerPreferencesEntityCreateRequest): TravelerPreferences {
        val preference = TravelerPreferences(
            traveler = createRequest.traveler,
            title = createRequest.title,
            description = createRequest.description
        )

        return repository.save(preference)
    }

    @Transactional
    override fun delete(preference: TravelerPreferences) {
        preference.id ?: throw IllegalArgumentException("TravelerPreferences id can not be null!")

        repository.delete(preference)
    }

}

data class TravelerPreferencesEntityCreateRequest(
    val traveler: Traveler,
    val title: String,
    val description: String?
)