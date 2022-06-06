package com.nikita.coursework.service

import com.nikita.coursework.entity.*
import com.nikita.coursework.reposiroty.JournalVisitRepository
import com.querydsl.core.BooleanBuilder
import org.springframework.data.crossstore.ChangeSetPersister
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate

interface JournalVisitService {
    fun getById(id: Long): JournalVisit
    fun getAll(): List<JournalVisit>
    fun create(createRequest: JournalVisitEntityCreateRequest, traveler: Traveler, operator: TourOperator, tour: Tour): JournalVisit
    fun update(journalVisit: JournalVisit, updateRequest: JournalVisitEntityUpdateRequest): JournalVisit
    fun softDelete(journalVisit: JournalVisit)
}

@Service
class JournalVisitServiceImpl(
    private val repository: JournalVisitRepository
): JournalVisitService {
    @Transactional(readOnly = true)
    override fun getById(id: Long): JournalVisit {
        val booleanBuilder = BooleanBuilder()
        booleanBuilder.and(QJournalVisit.journalVisit.id.eq(id))
        booleanBuilder.and(QJournalVisit.journalVisit.isDeleted.eq(false))
        return repository.findOne(booleanBuilder).orElseThrow() { throw ChangeSetPersister.NotFoundException() }
    }
    @Transactional(readOnly = true)
    override fun getAll(): List<JournalVisit> {
        val booleanBuilder = BooleanBuilder()

        return repository.findAll(booleanBuilder).toList()
    }

    @Transactional
    override fun create(
        createRequest: JournalVisitEntityCreateRequest,
        traveler: Traveler,
        operator: TourOperator,
        tour: Tour
    ): JournalVisit {
        val journalVisit = JournalVisit(
            traveler = traveler,
            operator = operator,
            tour = tour,
            visitDate = createRequest.visitDate,
            isArrived = createRequest.isArrived,
            isDeleted = false
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
    val visitDate: LocalDate,
    val isArrived: Boolean
)

data class JournalVisitEntityUpdateRequest(
    val visitDate: LocalDate,
    val isArrived: Boolean
)