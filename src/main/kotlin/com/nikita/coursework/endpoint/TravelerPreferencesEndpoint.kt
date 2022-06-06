package com.nikita.coursework.endpoint

import com.nikita.coursework.entity.Traveler
import com.nikita.coursework.service.TravelerPreferencesEntityCreateRequest
import com.nikita.coursework.service.TravelerPreferencesService
import com.nikita.coursework.service.TravelerService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

interface TravelerPreferencesEndpoint {
    fun getById(preferenceId: Long): TravelerPreferencesInfoDto
    fun getAll(): List<TravelerPreferencesInfoDto>
    fun create(createPayload: TravelerPreferencesEntityCreatePayload): TravelerPreferencesInfoDto
    fun delete(preferenceId: Long)
}

@Service
class TravelerPreferencesEndpointImpl(
    private val travelerPreferencesService: TravelerPreferencesService,
    private val travelerService: TravelerService
): TravelerPreferencesEndpoint {
    @Transactional(readOnly = true)
    override fun getById(preferenceId: Long): TravelerPreferencesInfoDto {
        val response = travelerPreferencesService.getById(preferenceId)

        return TravelerPreferencesInfoDto(
            id = response.id!!,
            traveler = TravelerShortInfoDto(
                id = response.traveler.id!!,
                shortName = getShortName(response.traveler)
            ),
            title = response.title,
            description = response.description
        )
    }

    @Transactional(readOnly = true)
    override fun getAll(): List<TravelerPreferencesInfoDto> {
        val response = travelerPreferencesService.getAll()

        return response.map {
            TravelerPreferencesInfoDto(
                id = it.id!!,
                traveler = TravelerShortInfoDto(
                    id = it.traveler.id!!,
                    shortName = getShortName(it.traveler)
                ),
                title = it.title,
                description = it.description
            )
        }
    }

    @Transactional
    override fun create(createPayload: TravelerPreferencesEntityCreatePayload): TravelerPreferencesInfoDto {
        val traveler = travelerService.getById(createPayload.travelerId)
        val response = travelerPreferencesService.create(createPayload.travelerPreferences, traveler)

        return TravelerPreferencesInfoDto(
            id = response.id!!,
            traveler = TravelerShortInfoDto(
                id = response.traveler.id!!,
                shortName = getShortName(response.traveler)
            ),
            title = response.title,
            description = response.description
        )
    }

    @Transactional
    override fun delete(preferenceId: Long) {
        val preference = travelerPreferencesService.getById(preferenceId)
        travelerPreferencesService.delete(preference)
    }

    private fun getShortName(traveler: Traveler): String {
            val nameS = traveler.firstName[0]
            val patronymicS = traveler.middleName[0]

            return "${traveler.lastName} $nameS.$patronymicS."
    }
}

data class TravelerPreferencesInfoDto(
    val id: Long,
    val traveler: TravelerShortInfoDto,
    val title: String,
    val description: String?
)

data class TravelerPreferencesEntityCreatePayload(
    val travelerId: Long,
    val travelerPreferences: TravelerPreferencesEntityCreateRequest
)