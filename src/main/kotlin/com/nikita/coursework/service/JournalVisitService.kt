package com.nikita.coursework.coursework.service

import com.nikita.coursework.coursework.entity.*
import com.nikita.coursework.coursework.repository.JournalVisitRepository
import com.querydsl.core.BooleanBuilder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate

interface JournalVisitService {
//    fun findById(id: Long): JournalVisit?
    fun create(createRequest: JournalVisitEntityCreateRequest): JournalVisit
    fun update(journalVisit: JournalVisit, updateRequest: JournalVisitEntityUpdateRequest): JournalVisit
    fun softDelete(journalVisit: JournalVisit)
}

@Service
class JournalVisitServiceImpl(
    private val repository: JournalVisitRepository
): JournalVisitService {
//    @Transactional(readOnly = true)
//    override fun findById(id: Long): JournalVisit? {
//        val booleanBuilder = BooleanBuilder()
//        booleanBuilder.and(QAirport.airport.id.eq(id))
//        booleanBuilder.and(QAirport.airport.idDeleted.eq(false))
//        return repository.findOne(booleanBuilder).orElseGet { null }
//    }

    @Transactional
    override fun create(createRequest: JournalVisitEntityCreateRequest): JournalVisit {
        val journalVisit = JournalVisit(
            traveler = createRequest.traveler,
            operator = createRequest.operator,
            tour = createRequest.tour,
            visitDate = createRequest.visitDate,
            isArrived = createRequest.isArrived,
            isDeleted = createRequest.isDeleted
        )

        return repository.save(journalVisit)
    }

    @Transactional
    override fun update(journalVisit: JournalVisit, updateRequest: JournalVisitEntityUpdateRequest): JournalVisit {
        journalVisit.id ?: throw IllegalArgumentException("JournalVisit id can not be null!")

        journalVisit.apply {
            this.visitDate = updateRequest.visitDate
            this.isArrived = updateRequest.isArrived
        }

        return repository.save(journalVisit)
    }

    @Transactional
    override fun softDelete(journalVisit: JournalVisit) {
        journalVisit.id ?: throw IllegalArgumentException("JournalVisit id can not be null!")

        journalVisit.apply {
            this.isDeleted = true
        }

        repository.save(journalVisit)
    }
}

data class JournalVisitEntityCreateRequest(
    val traveler: Traveler,
    val operator: TourOperator,
    val tour: Tour,
    val visitDate: LocalDate,
    val isArrived: Boolean,
    val isDeleted: Boolean
)

data class JournalVisitEntityUpdateRequest(
    val visitDate: LocalDate,
    val isArrived: Boolean
)