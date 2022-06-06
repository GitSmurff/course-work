package com.nikita.coursework.service

import com.nikita.coursework.entity.Traveler
import com.nikita.coursework.entity.TravelerPreferences
import com.nikita.coursework.reposiroty.TravelerPreferencesRepository
import com.querydsl.core.BooleanBuilder
import org.springframework.data.crossstore.ChangeSetPersister
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

interface TravelerPreferencesService {
    fun getById(id: Long): TravelerPreferences
    fun getAll(): List<TravelerPreferences>
    fun create(createRequest: TravelerPreferencesEntityCreateRequest, traveler: Traveler): TravelerPreferences
    fun delete(preference: TravelerPreferences)
}

@Service
class TravelerPreferencesServiceImpl(
    private val repository: TravelerPreferencesRepository
): TravelerPreferencesService {
    @Transactional(readOnly = true)
    override fun getById(id: Long): TravelerPreferences {
        return repository.findById(id).orElseThrow { throw ChangeSetPersister.NotFoundException() }
    }
    @Transactional(readOnly = true)
    override fun getAll(): List<TravelerPreferences> {
        val booleanBuilder = BooleanBuilder()

        return repository.findAll(booleanBuilder).toList()
    }

    @Transactional
    override fun create(createRequest: TravelerPreferencesEntityCreateRequest, traveler: Traveler): TravelerPreferences {
        val preference = TravelerPreferences(
            traveler = traveler,
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
    val title: String,
    val description: String?
)