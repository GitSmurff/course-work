package com.nikita.coursework.endpoint

import com.nikita.coursework.service.*
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate
import java.time.ZonedDateTime

interface JournalVisitEndpoint {
    fun getById(journalVisitId: Long): JournalVisitInfoDto
    fun getAll(): List<JournalVisitInfoDto>
    fun create(createPayload: JournalVisitEntityCreatePayload): JournalVisitInfoDto
    fun update(journalVisitId: Long, updateRequest: JournalVisitEntityUpdateRequest): JournalVisitInfoDto
    fun softDelete(journalVisitId: Long)
}

@Service
class JournalVisitEndpointImpl(
    private val journalVisitService: JournalVisitService,
    private val travelerService: TravelerService,
    private val operatorService: TourOperatorService,
    private val tourService: TourService
): JournalVisitEndpoint {

    @Transactional(readOnly = true)
    override fun getById(journalVisitId: Long): JournalVisitInfoDto {
        val response = journalVisitService.getById(journalVisitId)

        return JournalVisitInfoDto(
            id = response.id!!,
            traveler = TravelerShortInfoDto(
                id = response.traveler.id!!,
                shortName = getShortName(response.traveler.firstName, response.traveler.lastName, response.traveler.middleName)
            ),
            tourOperator = TourOperatorShortInfoDto(
                id = response.operator.id!!,
                shortName = getShortName(response.operator.firstName, response.operator.lastName, response.operator.middleName)
            ),
            tour = TourShortInfoDto(
                tourId = response.tour.id!!,
                tourName = response.tour.name,
                street = response.tour.location.street,
                startDate = response.tour.startDate,
                endDto = response.tour.endDate
            ),
            visitDate = response.visitDate,
            isArrived = response.isArrived,
            isDeleted = response.isDeleted,
            createdAt = response.createdAt,
            changedAt = response.changedAt
        )
    }

    @Transactional(readOnly = true)
    override fun getAll(): List<JournalVisitInfoDto> {
        val response = journalVisitService.getAll()

        return response.map {
            JournalVisitInfoDto(
                id = it.id!!,
                traveler = TravelerShortInfoDto(
                    id = it.traveler.id!!,
                    shortName = getShortName(it.traveler.firstName, it.traveler.lastName, it.traveler.middleName)
                ),
                tourOperator = TourOperatorShortInfoDto(
                    id = it.operator.id!!,
                    shortName = getShortName(
                        it.operator.firstName,
                        it.operator.lastName,
                        it.operator.middleName
                    )
                ),
                tour = TourShortInfoDto(
                    tourId = it.tour.id!!,
                    tourName = it.tour.name,
                    street = it.tour.location.street,
                    startDate = it.tour.startDate,
                    endDto = it.tour.endDate
                ),
                visitDate = it.visitDate,
                isArrived = it.isArrived,
                isDeleted = it.isDeleted,
                createdAt = it.createdAt,
                changedAt = it.changedAt
            )
        }
    }

    @Transactional
    override fun create(createPayload: JournalVisitEntityCreatePayload): JournalVisitInfoDto {
        val traveler = travelerService.getById(createPayload.travelerId)
        val operator = operatorService.getById(createPayload.operatorId)
        val tour = tourService.getById(createPayload.tourId)

        val response = journalVisitService.create(createPayload.journalVisit, traveler, operator, tour)

        return JournalVisitInfoDto(
            id = response.id!!,
            traveler = TravelerShortInfoDto(
                id = response.traveler.id!!,
                shortName = getShortName(response.traveler.firstName, response.traveler.lastName, response.traveler.middleName)
            ),
            tourOperator = TourOperatorShortInfoDto(
                id = response.operator.id!!,
                shortName = getShortName(response.operator.firstName, response.operator.lastName, response.operator.middleName)
            ),
            tour = TourShortInfoDto(
                tourId = response.tour.id!!,
                tourName = response.tour.name,
                street = response.tour.location.street,
                startDate = response.tour.startDate,
                endDto = response.tour.endDate
            ),
            visitDate = response.visitDate,
            isArrived = response.isArrived,
            isDeleted = response.isDeleted,
            createdAt = response.createdAt,
            changedAt = response.changedAt
        )
    }

    @Transactional
    override fun update(journalVisitId: Long, updateRequest: JournalVisitEntityUpdateRequest): JournalVisitInfoDto {
        val journalVisit = journalVisitService.getById(journalVisitId)
        val response = journalVisitService.update(journalVisit, updateRequest)

        return JournalVisitInfoDto(
            id = response.id!!,
            traveler = TravelerShortInfoDto(
                id = response.traveler.id!!,
                shortName = getShortName(response.traveler.firstName, response.traveler.lastName, response.traveler.middleName)
            ),
            tourOperator = TourOperatorShortInfoDto(
                id = response.operator.id!!,
                shortName = getShortName(response.operator.firstName, response.operator.lastName, response.operator.middleName)
            ),
            tour = TourShortInfoDto(
                tourId = response.tour.id!!,
                tourName = response.tour.name,
                street = response.tour.location.street,
                startDate = response.tour.startDate,
                endDto = response.tour.endDate
            ),
            visitDate = response.visitDate,
            isArrived = response.isArrived,
            isDeleted = response.isDeleted,
            createdAt = response.createdAt,
            changedAt = response.changedAt
        )
    }

    @Transactional
    override fun softDelete(journalVisitId: Long) {
        val journalVisit = journalVisitService.getById(journalVisitId)
        journalVisitService.softDelete(journalVisit)
    }

    private fun getShortName(firstName: String, lastName: String, middleName: String): String {
        val nameS = firstName[0]
        val patronymicS = middleName[0]

        return "$lastName $nameS.$patronymicS."
    }
}

data class JournalVisitInfoDto(
    val id: Long,
    val traveler: TravelerShortInfoDto,
    val tourOperator: TourOperatorShortInfoDto,
    val tour: TourShortInfoDto,
    val visitDate: LocalDate,
    val isArrived: Boolean,
    val isDeleted: Boolean,
    val createdAt: ZonedDateTime?,
    val changedAt: ZonedDateTime?
)

data class JournalVisitEntityCreatePayload(
    val travelerId: Long,
    val operatorId: Long,
    val tourId: Long,
    val journalVisit: JournalVisitEntityCreateRequest
)