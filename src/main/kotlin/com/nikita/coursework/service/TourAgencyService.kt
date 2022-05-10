package com.nikita.coursework.coursework.service

import com.nikita.coursework.coursework.entity.Location
import com.nikita.coursework.coursework.entity.TourAgency
import com.nikita.coursework.coursework.repository.TourAgencyRepository
import com.querydsl.core.BooleanBuilder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

interface TourAgencyService {
//    fun findById(id: Long): TourAgency?
    fun create(createRequest: TourAgencyEntityCreateRequest): TourAgency
    fun softDelete(tourAgency: TourAgency)
}

@Service
class TourAgencyServiceImpl(
    private val repository: TourAgencyRepository
): TourAgencyService {
//    @Transactional(readOnly = true)
//    override fun findById(id: Long): TourAgency? {
//        val booleanBuilder = BooleanBuilder()
//        booleanBuilder.and(QTourAgency.tourAgency.id.eq(id))
//        booleanBuilder.and(QTourAgency.tourAgency.idDeleted.eq(false))
//        return repository.findOne(booleanBuilder).orElseGet { null }
//    }

    @Transactional
    override fun create(createRequest: TourAgencyEntityCreateRequest): TourAgency {
        val tourAgency = TourAgency(
            name = createRequest.name,
            location = createRequest.location,
            isDeleted = createRequest.isDeleted
        )

        return repository.save(tourAgency)
    }

    @Transactional
    override fun softDelete(tourAgency: TourAgency) {
        tourAgency.id ?: throw IllegalArgumentException("TourAgency id can not be null!")

        tourAgency.apply {
            this.isDeleted = true
        }

        repository.save(tourAgency)
    }
}

data class TourAgencyEntityCreateRequest(
    val name: String,
    val location: Location?,
    val isDeleted: Boolean
)