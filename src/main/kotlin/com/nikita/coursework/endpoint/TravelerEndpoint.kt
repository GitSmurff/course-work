package com.nikita.coursework.endpoint

import com.nikita.coursework.entity.Location
import com.nikita.coursework.entity.LocationSourceType
import com.nikita.coursework.service.LocationCreateService
import com.nikita.coursework.service.TravelerEntityCreateRequest
import com.nikita.coursework.service.TravelerEntityUpdateRequest
import com.nikita.coursework.service.TravelerService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.ZonedDateTime

interface TravelerEndpoint {
    fun getById(travelerId: Long): TravelerInfoDto
    fun getAll(): List<TravelerInfoDto>
    fun create(createPayload: TravelerEntityCreatePayload): TravelerInfoDto
    fun update(travelerId: Long, updateRequest: TravelerEntityUpdateRequest): TravelerInfoDto
    fun softDelete(travelerId: Long)
}

@Service
class TravelerEndpointImpl(
    private val travelerService: TravelerService,
    private val locationCreateService: LocationCreateService
): TravelerEndpoint {
    @Transactional(readOnly = true)
    override fun getById(travelerId: Long): TravelerInfoDto {
        val response = travelerService.getById(travelerId)

        return TravelerInfoDto(
            id = response.id!!,
            firstName = response.firstName,
            lastName = response.lastName,
            middleName = response.middleName,
            phoneNumber = response.phoneNumber,
            location = LocationInfoDto(
                id = response.location.id!!,
                cityId = response.location.city.id!!,
                cityName = response.location.city.name,
                countryId = response.location.city.country.id!!,
                countryName = response.location.city.country.name,
                countryCode = response.location.city.country.code,
                street = response.location.street,
                lat = response.location.lat,
                lng = response.location.lng,
                source = response.location.source
            ),
            isDeleted = response.isDeleted,
            createdAt = response.createdAt,
            changedAt = response.changedAt
        )
    }

    @Transactional(readOnly = true)
    override fun getAll(): List<TravelerInfoDto> {
        val response = travelerService.getAll()

        return response.map {
            TravelerInfoDto(
                id = it.id!!,
                firstName = it.firstName,
                lastName = it.lastName,
                middleName = it.middleName,
                phoneNumber = it.phoneNumber,
                location = LocationInfoDto(
                    id = it.location.id!!,
                    cityId = it.location.city.id!!,
                    cityName = it.location.city.name,
                    countryId = it.location.city.country.id!!,
                    countryName = it.location.city.country.name,
                    countryCode = it.location.city.country.code,
                    street = it.location.street,
                    lat = it.location.lat,
                    lng = it.location.lng,
                    source = it.location.source
                ),
                isDeleted = it.isDeleted,
                createdAt = it.createdAt,
                changedAt = it.changedAt
            )
        }
    }

    @Transactional
    override fun create(createPayload: TravelerEntityCreatePayload): TravelerInfoDto {
        val locationResponse = locationCreateService.createPayload(createPayload.location, LocationSourceType.TRAVELER)
        val response = travelerService.create(createPayload.traveler, locationResponse)

        return TravelerInfoDto(
            id = response.id!!,
            firstName = response.firstName,
            lastName = response.lastName,
            middleName = response.middleName,
            phoneNumber = response.phoneNumber,
            location = LocationInfoDto(
                id = response.location.id!!,
                cityId = response.location.city.id!!,
                cityName = response.location.city.name,
                countryId = response.location.city.country.id!!,
                countryName = response.location.city.country.name,
                countryCode = response.location.city.country.code,
                street = response.location.street,
                lat = response.location.lat,
                lng = response.location.lng,
                source = response.location.source
            ),
            isDeleted = response.isDeleted,
            createdAt = response.createdAt,
            changedAt = response.changedAt
        )
    }

    @Transactional
    override fun update(travelerId: Long, updateRequest: TravelerEntityUpdateRequest): TravelerInfoDto {
        val traveler = travelerService.getById(travelerId)
        val response = travelerService.update(traveler, updateRequest)

        return TravelerInfoDto(
            id = response.id!!,
            firstName = response.firstName,
            lastName = response.lastName,
            middleName = response.middleName,
            phoneNumber = response.phoneNumber,
            location = LocationInfoDto(
                id = response.location.id!!,
                cityId = response.location.city.id!!,
                cityName = response.location.city.name,
                countryId = response.location.city.country.id!!,
                countryName = response.location.city.country.name,
                countryCode = response.location.city.country.code,
                street = response.location.street,
                lat = response.location.lat,
                lng = response.location.lng,
                source = response.location.source
            ),
            isDeleted = response.isDeleted,
            createdAt = response.createdAt,
            changedAt = response.changedAt
        )
    }

    @Transactional
    override fun softDelete(travelerId: Long) {
        val traveler = travelerService.getById(travelerId)
        travelerService.softDelete(traveler)
    }

    private fun getAddress(location: Location?): String {
        if (location == null) {
            return ""
        }
        return "${location.city.name}, ${location.city.country.name}"
    }
}

data class TravelerInfoDto(
    val id: Long,
    val firstName: String,
    val lastName: String,
    val middleName: String,
    val phoneNumber: String?,
    val location: LocationInfoDto,
    val isDeleted: Boolean,
    val createdAt: ZonedDateTime?,
    val changedAt: ZonedDateTime?
)

data class TravelerShortInfoDto(
    val id: Long,
    val shortName: String
)

data class TravelerEntityCreatePayload(
    val traveler: TravelerEntityCreateRequest,
    val location: LocationEntityCreatePayload
)