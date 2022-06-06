package com.nikita.coursework.endpoint

import com.nikita.coursework.entity.LocationSourceType
import com.nikita.coursework.service.*
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.ZonedDateTime

interface AirportEndpoint {
    fun getById(airportId: Long): AirportInfoDto
    fun getAll(): List<AirportInfoDto>
    fun create(createPayload: AirportEntityCreatePayload): AirportInfoDto
    fun softDelete(airportId: Long)
}

@Service
class AirportEndpointImpl(
    private val airportService: AirportService,
    private val locationCreateService: LocationCreateService
): AirportEndpoint {

    @Transactional(readOnly = true)
    override fun getById(airportId: Long): AirportInfoDto {
        val response = airportService.getById(airportId)

        return AirportInfoDto(
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
    override fun getAll(): List<AirportInfoDto> {
        val response = airportService.getAll()

        return response.map {
            AirportInfoDto(
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
    override fun create(createPayload: AirportEntityCreatePayload): AirportInfoDto {
        val locationResponse = locationCreateService.createPayload(createPayload.location, LocationSourceType.AIRPORT)
        val response = airportService.create(createPayload.airport, locationResponse)

        return AirportInfoDto(
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
    override fun softDelete(airportId: Long) {
        val airport = airportService.getById(airportId)
        airportService.softDelete(airport)
    }
}

data class AirportInfoDto(
    val id: Long,
    val name: String,
    val location: LocationInfoDto,
    val isDeleted: Boolean,
    val createdAt: ZonedDateTime?,
    val changedAt: ZonedDateTime?
)

data class AirportShortInfoDto(
    val id: Long,
    val name: String
)

data class AirportEntityCreatePayload(
    val airport: AirportEntityCreateRequest,
    val location: LocationEntityCreatePayload
)