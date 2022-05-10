package com.nikita.coursework.coursework.service

import com.nikita.coursework.coursework.entity.TourAgency
import com.nikita.coursework.coursework.entity.TourOperator
import com.nikita.coursework.coursework.repository.TourOperatorRepository
import com.querydsl.core.BooleanBuilder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

interface TourOperatorService {
//    fun findById(id: Long): TourOperator?
    fun create(createRequest: TourOperatorEntityCreateRequest): TourOperator
    fun softDelete(tourOperator: TourOperator)
}

@Service
class TourOperatorServiceImpl(
    private val repository: TourOperatorRepository
): TourOperatorService {

//    @Transactional(readOnly = true)
//    override fun findById(id: Long): TourOperator? {
//        val booleanBuilder = BooleanBuilder()
//        booleanBuilder.and(QTourOperator.tourOperator.id.eq(id))
//        booleanBuilder.and(QTourOperator.tourOperator.idDeleted.eq(false))
//        return repository.findOne(booleanBuilder).orElseGet { null }
//    }

    @Transactional
    override fun create(createRequest: TourOperatorEntityCreateRequest): TourOperator {
        val tourOperator = TourOperator(
            agency = createRequest.agency,
            firstName = createRequest.firstName,
            lastName = createRequest.lastName,
            middleName = createRequest.middleName,
            isDeleted = createRequest.isDeleted
        )

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
    val agency: TourAgency,
    val firstName: String,
    val lastName: String,
    val middleName: String,
    val isDeleted: Boolean
)