package com.nikita.coursework.endpoint

import com.nikita.coursework.entity.LocationSourceType
import com.nikita.coursework.entity.Traveler
import com.nikita.coursework.service.*
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.math.BigDecimal
import java.time.LocalDate
import java.time.ZonedDateTime

interface TourEndpoint {
    fun getById(tourId: Long): TourInfoDto
    fun getAll(): List<TourInfoDto>
    fun create(createPayload: TourEntityCreatePayload): TourInfoDto
    fun softDelete(tourId: Long)
}

@Service
class TourEndpointImpl(
    private val tourService: TourService,
    private val locationCreateService: LocationCreateService,
    private val travelerService: TravelerService,
    private val agencyService: TourAgencyService
): TourEndpoint {

    @Transactional(readOnly = true)
    override fun getById(tourId: Long): TourInfoDto {
        val response = tourService.getById(tourId)

        return TourInfoDto(
            id = response.id!!,
            traveler = TravelerShortInfoDto(
                id = response.traveler.id!!,
                shortName = getShortName(response.traveler)
            ),
            tourAgency = TourAgencyShortInfoDto(
                id = response.agency.id!!,
                name = response.agency.name
            ),
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
            name = response.name,
            description = response.description,
            price = response.price,
            isDeleted = response.isDeleted,
            startDate = response.startDate,
            endDate = response.endDate,
            createdAt = response.createdAt,
            changedAt = response.changedAt
        )
    }

    @Transactional(readOnly = true)
    override fun getAll(): List<TourInfoDto> {
        val response = tourService.getAll()

        return response.map {
            TourInfoDto(
                id = it.id!!,
                traveler = TravelerShortInfoDto(
                    id = it.traveler.id!!,
                    shortName = getShortName(it.traveler)
                ),
                tourAgency = TourAgencyShortInfoDto(
                    id = it.agency.id!!,
                    name = it.agency.name
                ),
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
                name = it.name,
                description = it.description,
                price = it.price,
                isDeleted = it.isDeleted,
                startDate = it.startDate,
                endDate = it.endDate,
                createdAt = it.createdAt,
                changedAt = it.changedAt
            )
        }
    }

    @Transactional
    override fun create(createPayload: TourEntityCreatePayload): TourInfoDto {
        val traveler = travelerService.getById(createPayload.travelerId)
        val agency = agencyService.getById(createPayload.agencyId)
        val locationResponse = locationCreateService.createPayload(createPayload.location, LocationSourceType.TOUR)
        val response = tourService.create(createPayload.tour, locationResponse, traveler, agency)

        return TourInfoDto(
            id = response.id!!,
            traveler = TravelerShortInfoDto(
                id = response.traveler.id!!,
                shortName = getShortName(response.traveler)
            ),
            tourAgency = TourAgencyShortInfoDto(
                id = response.agency.id!!,
                name = response.agency.name
            ),
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
            name = response.name,
            description = response.description,
            price = response.price,
            isDeleted = response.isDeleted,
            startDate = response.startDate,
            endDate = response.endDate,
            createdAt = response.createdAt,
            changedAt = response.changedAt
        )
    }

    @Transactional
    override fun softDelete(tourId: Long) {
        val tour = tourService.getById(id = tourId)
        tourService.softDelete(tour)
    }

    private fun getShortName(traveler: Traveler): String {
        val nameS = traveler.firstName[0]
        val patronymicS = traveler.middleName[0]

        return "${traveler.lastName} $nameS.$patronymicS."
    }

}

data class TourInfoDto(
    val id: Long,
    val traveler: TravelerShortInfoDto,
    val tourAgency: TourAgencyShortInfoDto,
    val location: LocationInfoDto,
    val name: String,
    val description: String?,
    val price: BigDecimal,
    val isDeleted: Boolean,
    val startDate: LocalDate,
    val endDate: LocalDate,
    val createdAt: ZonedDateTime?,
    val changedAt: ZonedDateTime?
)

data class TourShortInfoDto(
    val tourId: Long,
    val tourName: String,
    val street: String?,
    val startDate: LocalDate,
    val endDto: LocalDate
)

data class TourEntityCreatePayload(
    val travelerId: Long,
    val agencyId: Long,
    val tour: TourEntityCreateRequest,
    val location: LocationEntityCreatePayload
)