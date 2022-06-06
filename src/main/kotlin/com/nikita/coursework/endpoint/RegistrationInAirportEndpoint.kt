package com.nikita.coursework.endpoint

import com.nikita.coursework.entity.RegistrationStatus
import com.nikita.coursework.entity.Traveler
import com.nikita.coursework.service.*
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate
import java.time.ZonedDateTime

interface RegistrationInAirportEndpoint {
    fun findById(registrationId: Long): RegistrationInAirportInfoDto
    fun getAll(): List<RegistrationInAirportInfoDto>
    fun create(createPayload: RegistrationInAirportEntityCreatePayload): RegistrationInAirportInfoDto
    fun update(registrationId: Long, updateRequest: RegistrationInAirportEntityUpdateRequest): RegistrationInAirportInfoDto
    fun softDelete(registrationId: Long)
}

@Service
class RegistrationInAirportEndpointImpl(
    private val registrationInAirportService: RegistrationInAirportService,
    private val travelerService: TravelerService,
    private val airportService: AirportService
): RegistrationInAirportEndpoint {

    @Transactional(readOnly = true)
    override fun findById(registrationId: Long): RegistrationInAirportInfoDto {
        val response = registrationInAirportService.getById(registrationId)

        return RegistrationInAirportInfoDto(
            id = response.id!!,
            traveler = TravelerShortInfoDto(
                id = response.traveler.id!!,
                shortName = getShortName(response.traveler)
            ),
            airport = AirportShortInfoDto(
                id = response.airport.id!!,
                name = response.airport.name
            ),
            arrivedDate = response.arrivedDate,
            departedDate = response.departedDate,
            status = response.status,
            isDeleted = response.isDeleted,
            createdAt = response.createdAt,
            changedAt = response.changedAt
        )
    }

    @Transactional(readOnly = true)
    override fun getAll(): List<RegistrationInAirportInfoDto> {
        val response = registrationInAirportService.getAll()

        return response.map {
            RegistrationInAirportInfoDto(
                id = it.id!!,
                traveler = TravelerShortInfoDto(
                    id = it.traveler.id!!,
                    shortName = getShortName(it.traveler)
                ),
                airport = AirportShortInfoDto(
                    id = it.airport.id!!,
                    name = it.airport.name
                ),
                arrivedDate = it.arrivedDate,
                departedDate = it.departedDate,
                status = it.status,
                isDeleted = it.isDeleted,
                createdAt = it.createdAt,
                changedAt = it.changedAt
            )
        }
    }

    @Transactional
    override fun create(createPayload: RegistrationInAirportEntityCreatePayload): RegistrationInAirportInfoDto {
        val traveler = travelerService.getById(createPayload.travelerId)
        val airport = airportService.getById(createPayload.airportId)
        val response = registrationInAirportService.create(createPayload.registration, traveler, airport)

        return RegistrationInAirportInfoDto(
            id = response.id!!,
            traveler = TravelerShortInfoDto(
                id = response.traveler.id!!,
                shortName = getShortName(response.traveler)
            ),
            airport = AirportShortInfoDto(
                id = response.airport.id!!,
                name = response.airport.name
            ),
            arrivedDate = response.arrivedDate,
            departedDate = response.departedDate,
            status = response.status,
            isDeleted = response.isDeleted,
            createdAt = response.createdAt,
            changedAt = response.changedAt
        )
    }

    @Transactional
    override fun update(
        registrationId: Long,
        updateRequest: RegistrationInAirportEntityUpdateRequest
    ): RegistrationInAirportInfoDto {
        val registration = registrationInAirportService.getById(registrationId)
        val response = registrationInAirportService.update(registration, updateRequest)

        return RegistrationInAirportInfoDto(
            id = response.id!!,
            traveler = TravelerShortInfoDto(
                id = response.traveler.id!!,
                shortName = getShortName(response.traveler)
            ),
            airport = AirportShortInfoDto(
                id = response.airport.id!!,
                name = response.airport.name
            ),
            arrivedDate = response.arrivedDate,
            departedDate = response.departedDate,
            status = response.status,
            isDeleted = response.isDeleted,
            createdAt = response.createdAt,
            changedAt = response.changedAt
        )
    }

    @Transactional
    override fun softDelete(registrationId: Long) {
        val registration = registrationInAirportService.getById(registrationId)
        registrationInAirportService.softDelete(registration)

    }

    private fun getShortName(traveler: Traveler): String {
        val nameS = traveler.firstName[0]
        val patronymicS = traveler.middleName[0]

        return "${traveler.lastName} $nameS.$patronymicS."
    }
}

data class RegistrationInAirportInfoDto(
    val id: Long,
    val traveler: TravelerShortInfoDto,
    val airport: AirportShortInfoDto,
    val arrivedDate: LocalDate,
    val departedDate: LocalDate,
    val status: RegistrationStatus,
    val isDeleted: Boolean,
    val createdAt: ZonedDateTime?,
    val changedAt: ZonedDateTime?
)

data class RegistrationInAirportEntityCreatePayload(
    val travelerId: Long,
    val airportId: Long,
    val registration: RegistrationInAirportEntityCreateRequest
)