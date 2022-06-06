package com.nikita.coursework.service

import com.nikita.coursework.entity.QTourOperator
import com.nikita.coursework.entity.TourAgency
import com.nikita.coursework.entity.TourOperator
import com.nikita.coursework.reposiroty.TourOperatorRepository
import com.querydsl.core.BooleanBuilder
import org.springframework.data.crossstore.ChangeSetPersister
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

interface TourOperatorService {
    fun getById(id: Long): TourOperator
    fun getAll(): List<TourOperator>
    fun create(createRequest: TourOperatorEntityCreateRequest, agency: TourAgency): TourOperator
    fun update(tourOperator: TourOperator, updateRequest: TourOperatorEntityUpdateRequest): TourOperator
    fun softDelete(tourOperator: TourOperator)
}

@Service
class TourOperatorServiceImpl(
    private val repository: TourOperatorRepository
): TourOperatorService {

    @Transactional(readOnly = true)
    override fun getById(id: Long): TourOperator {
        val booleanBuilder = BooleanBuilder()
        booleanBuilder.and(QTourOperator.tourOperator.id.eq(id))
        booleanBuilder.and(QTourOperator.tourOperator.isDeleted.eq(false))
        return repository.findOne(booleanBuilder).orElseThrow { throw ChangeSetPersister.NotFoundException() }
    }
    @Transactional(readOnly = true)
    override fun getAll(): List<TourOperator> {
        val booleanBuilder = BooleanBuilder()

        return repository.findAll(booleanBuilder).toList()
    }

    @Transactional
    override fun create(createRequest: TourOperatorEntityCreateRequest, agency: TourAgency): TourOperator {
        val tourOperator = TourOperator(
            agency = agency,
            firstName = createRequest.firstName,
            lastName = createRequest.lastName,
            middleName = createRequest.middleName,
            isDeleted = createRequest.isDeleted
        )

        return repository.save(tourOperator)
    }

    @Transactional
    override fun update(tourOperator: TourOperator, updateRequest: TourOperatorEntityUpdateRequest): TourOperator {
        tourOperator.id ?: throw IllegalArgumentException("TourOperator id can not be null!")

        tourOperator.apply {
            this.firstName = updateRequest.firstName
            this.lastName  =updateRequest.lastName
            this.middleName = updateRequest.middleName
        }

        return repository.save(tourOperator)
    }

    @Transactional
    override fun softDelete(tourOperator: TourOperator) {
        tourOperator.id ?: throw IllegalArgumentException("TourOperator id can not be null!")

        tourOperator.apply {
            this.isDeleted = true
        }

        repository.save(tourOperator)
    }
}

data class TourOperatorEntityCreateRequest(
    val firstName: String,
    val lastName: String,
    val middleName: String,
    val isDeleted: Boolean
)
data class TourOperatorEntityUpdateRequest(
    val firstName: String,
    val lastName: String,
    val middleName: String
)