package com.nikita.coursework.endpoint

import com.nikita.coursework.entity.Traveler
import com.nikita.coursework.entity.TravelerGeoPositionStatusType
import com.nikita.coursework.service.TravelerGeoPositionHistoryEntityCreateRequest
import com.nikita.coursework.service.TravelerGeoPositionHistoryService
import com.nikita.coursework.service.TravelerService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.math.BigDecimal
import java.time.ZonedDateTime

interface TravelerGeoPositionHistoryEndpoint {
    fun getById(travelerGeoPositionId: Long): TravelerGeoPositionHistoryInfoDto
    fun getAll(): List<TravelerGeoPositionHistoryInfoDto>
    fun create(createPayload: TravelerGeoPositionEntityCreatePayload): TravelerGeoPositionHistoryInfoDto
    fun softDelete(travelerGeoPositionId: Long)
}

@Service
class TravelerGeoPositionHistoryEndpointImpl(
    private val travelerGeoPositionHistoryService: TravelerGeoPositionHistoryService,
    private val travelerService: TravelerService
): TravelerGeoPositionHistoryEndpoint {
    @Transactional(readOnly = true)
    override fun getById(travelerGeoPositionId: Long): TravelerGeoPositionHistoryInfoDto {
        val response = travelerGeoPositionHistoryService.getById(travelerGeoPositionId)

        return TravelerGeoPositionHistoryInfoDto(
            id = response.id!!,
            traveler = TravelerShortInfoDto(
                id = response.traveler.id!!,
                shortName = getShortName(response.traveler)
            ),
            lat = response.lat,
            lng = response.lng,
            status = response.status,
            createdAt = response.createdAt,
            changedAt = response.changedAt
        )
    }

    @Transactional(readOnly = true)
    override fun getAll(): List<TravelerGeoPositionHistoryInfoDto> {
        val response = travelerGeoPositionHistoryService.getAll()

        return response.map {
            TravelerGeoPositionHistoryInfoDto(
                id = it.id!!,
                traveler = TravelerShortInfoDto(
                    id = it.traveler.id!!,
                    shortName = getShortName(it.traveler)
                ),
                lat = it.lat,
                lng = it.lng,
                status = it.status,
                createdAt = it.createdAt,
                changedAt = it.changedAt
            )
        }
    }

    @Transactional
    override fun create(createPayload: TravelerGeoPositionEntityCreatePayload): TravelerGeoPositionHistoryInfoDto {
        val traveler = travelerService.getById(createPayload.travelerId)
        val response = travelerGeoPositionHistoryService.create(createPayload.travelerGeoPosition, traveler)

        return TravelerGeoPositionHistoryInfoDto(
            id = response.id!!,
            traveler = TravelerShortInfoDto(
                id = response.traveler.id!!,
                shortName = getShortName(response.traveler)
            ),
            lat = response.lat,
            lng = response.lng,
            status = response.status,
            createdAt = response.createdAt,
            changedAt = response.changedAt
        )
    }

    @Transactional
    override fun softDelete(travelerGeoPositionId: Long) {
        val tour = travelerGeoPositionHistoryService.getById(id = travelerGeoPositionId)
        travelerGeoPositionHistoryService.softDelete(tour)
    }

    private fun getShortName(traveler: Traveler): String {
        val nameS = traveler.firstName[0]
        val patronymicS = traveler.middleName[0]

        return "${traveler.lastName} $nameS.$patronymicS."
    }
}

data class TravelerGeoPositionHistoryInfoDto(
    val id: Long,
    val traveler: TravelerShortInfoDto,
    val lat: BigDecimal,
    val lng: BigDecimal,
    val status: TravelerGeoPositionStatusType,
    val createdAt: ZonedDateTime?,
    val changedAt: ZonedDateTime?
)

data class TravelerGeoPositionEntityCreatePayload(
    val travelerId: Long,
    val travelerGeoPosition: TravelerGeoPositionHistoryEntityCreateRequest
)