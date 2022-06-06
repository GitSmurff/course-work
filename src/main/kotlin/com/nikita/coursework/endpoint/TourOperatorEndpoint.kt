package com.nikita.coursework.endpoint

import com.nikita.coursework.service.TourAgencyService
import com.nikita.coursework.service.TourOperatorEntityCreateRequest
import com.nikita.coursework.service.TourOperatorEntityUpdateRequest
import com.nikita.coursework.service.TourOperatorService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.ZonedDateTime

interface TourOperatorEndpoint {
    fun getById(operatorId: Long): TourOperatorInfoDto
    fun getAll(): List<TourOperatorInfoDto>
    fun create(createPayload: TourOperatorEntityCreatePayload): TourOperatorInfoDto
    fun update(operatorId: Long, updateRequest: TourOperatorEntityUpdateRequest): TourOperatorInfoDto
    fun softDelete(operatorId: Long)
}

@Service
class TourOperatorEndpointImpl(
    private val tourOperatorService: TourOperatorService,
    private val agencyService: TourAgencyService
): TourOperatorEndpoint {

    @Transactional(readOnly = true)
    override fun getById(operatorId: Long): TourOperatorInfoDto {
        val response = tourOperatorService.getById(operatorId)

        return TourOperatorInfoDto(
            id = response.id!!,
            tourAgency = TourAgencyShortInfoDto(
                id = response.agency.id!!,
                name = response.agency.name
            ),
            firstName = response.firstName,
            lastName = response.lastName,
            middleName = response.middleName,
            isDeleted = response.isDeleted,
            createdAt = response.createdAt,
            changedAt = response.changedAt
        )
    }

    @Transactional(readOnly = true)
    override fun getAll(): List<TourOperatorInfoDto> {
        val response = tourOperatorService.getAll()

        return response.map {
            TourOperatorInfoDto(
                id = it.id!!,
                tourAgency = TourAgencyShortInfoDto(
                    id = it.agency.id!!,
                    name = it.agency.name
                ),
                firstName = it.firstName,
                lastName = it.lastName,
                middleName = it.middleName,
                isDeleted = it.isDeleted,
                createdAt = it.createdAt,
                changedAt = it.changedAt
            )
        }
    }

    @Transactional
    override fun create(createPayload: TourOperatorEntityCreatePayload): TourOperatorInfoDto {
        val agency = agencyService.getById(createPayload.agencyId)
        val response = tourOperatorService.create(createPayload.tourOperator, agency)

        return TourOperatorInfoDto(
            id = response.id!!,
            tourAgency = TourAgencyShortInfoDto(
                id = response.agency.id!!,
                name = response.agency.name
            ),
            firstName = response.firstName,
            lastName = response.lastName,
            middleName = response.middleName,
            isDeleted = response.isDeleted,
            createdAt = response.createdAt,
            changedAt = response.changedAt
        )
    }

    @Transactional
    override fun update(operatorId: Long, updateRequest: TourOperatorEntityUpdateRequest): TourOperatorInfoDto {
        val operator = tourOperatorService.getById(operatorId)
        val response = tourOperatorService.update(operator, updateRequest)

        return TourOperatorInfoDto(
            id = response.id!!,
            tourAgency = TourAgencyShortInfoDto(
                id = response.agency.id!!,
                name = response.agency.name
            ),
            firstName = response.firstName,
            lastName = response.lastName,
            middleName = response.middleName,
            isDeleted = response.isDeleted,
            createdAt = response.createdAt,
            changedAt = response.changedAt
        )
    }

    @Transactional
    override fun softDelete(operatorId: Long) {
        val operator = tourOperatorService.getById(operatorId)
        tourOperatorService.softDelete(operator)
    }
}

data class TourOperatorInfoDto(
    val id: Long,
    val tourAgency: TourAgencyShortInfoDto,
    val firstName: String,
    val lastName: String,
    val middleName: String,
    val isDeleted: Boolean,
    val createdAt: ZonedDateTime?,
    val changedAt: ZonedDateTime?
)

data class TourOperatorShortInfoDto(
    val id: Long,
    val shortName: String
)

data class TourOperatorEntityCreatePayload(
    val agencyId: Long,
    val tourOperator: TourOperatorEntityCreateRequest
)