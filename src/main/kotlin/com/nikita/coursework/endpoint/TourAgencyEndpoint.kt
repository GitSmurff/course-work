package com.nikita.coursework.endpoint

import com.nikita.coursework.entity.LocationSourceType
import com.nikita.coursework.service.LocationCreateService
import com.nikita.coursework.service.TourAgencyEntityCreateRequest
import com.nikita.coursework.service.TourAgencyService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.ZonedDateTime

interface TourAgencyEndpoint {
    fun getById(agencyId: Long): TourAgencyInfoDto
    fun getAll(): List<TourAgencyInfoDto>
    fun create(createPayload: TourAgencyEntityCreatePayload): TourAgencyInfoDto
    fun softDelete(agencyId: Long)
}

@Service
class TourAgencyEndpointImpl(
    private val tourAgencyService: TourAgencyService,
    private val locationCreateService: LocationCreateService
): TourAgencyEndpoint {
    @Transactional(readOnly = true)
    override fun getById(agencyId: Long): TourAgencyInfoDto {
        val response = tourAgencyService.getById(agencyId)

        return TourAgencyInfoDto(
            id = response.id!!,
            name = response.name,
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
    override fun getAll(): List<TourAgencyInfoDto> {
        val response = tourAgencyService.getAll()

        return response.map {
            TourAgencyInfoDto(
                id = it.id!!,
                name = it.name,
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
    override fun create(createPayload: TourAgencyEntityCreatePayload): TourAgencyInfoDto {
        val locationResponse = locationCreateService.createPayload(createPayload.location, LocationSourceType.AGENCY)
        val response = tourAgencyService.create(createPayload.agency, locationResponse)

        return TourAgencyInfoDto(
            id = response.id!!,
            name = response.name,
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
    override fun softDelete(agencyId: Long) {
        val agency = tourAgencyService.getById(agencyId)
        tourAgencyService.softDelete(agency)
    }
}

data class TourAgencyInfoDto(
    val id: Long,
    val name: String,
    val location: LocationInfoDto,
    val isDeleted: Boolean,
    val createdAt: ZonedDateTime?,
    val changedAt: ZonedDateTime?
)

data class TourAgencyShortInfoDto(
    val id: Long,
    val name: String
)

data class TourAgencyEntityCreatePayload(
    val agency: TourAgencyEntityCreateRequest,
    val location: LocationEntityCreatePayload
)