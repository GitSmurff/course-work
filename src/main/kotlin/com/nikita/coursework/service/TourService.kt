package com.nikita.coursework.coursework.service

import com.nikita.coursework.coursework.entity.*
import com.nikita.coursework.coursework.repository.TourRepository
import com.querydsl.core.BooleanBuilder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.math.BigDecimal
import java.time.LocalDate

interface TourService {
//    fun findById(id: Long): Tour?
    fun create(createRequest: TourEntityCreateRequest): Tour
    fun softDelete(tour: Tour)
}

@Service
class TourServiceImpl(
    private val repository: TourRepository
): TourService {
//    @Transactional(readOnly = true)
//    override fun findById(id: Long): Tour? {
//        val booleanBuilder = BooleanBuilder()
//        booleanBuilder.and(QTour.tour.id.eq(id))
//        booleanBuilder.and(QTour.tour.idDeleted.eq(false))
//        return repository.findOne(booleanBuilder).orElseGet { null }
//    }

    @Transactional
    override fun create(createRequest: TourEntityCreateRequest): Tour {
        val tour = Tour(
            traveler = createRequest.traveler,
                    agency = createRequest.agency,
                    location = createRequest.location,
                    tourType = createRequest.tourType,
                    name = createRequest.name,
                    description = createRequest.description,
                    price = createRequest.price,
                    isDeleted = createRequest.isDeleted,
                    startDate = createRequest.startDate,
                    endDate = createRequest.endDate
        )

        return repository.save(tour)
    }

    @Transactional
    override fun softDelete(tour: Tour) {
        tour.id ?: throw IllegalArgumentException("Tour id can not be null!")

        tour.apply {
            this.isDeleted = true
        }

        repository.save(tour)
    }
}

data class TourEntityCreateRequest(
    val traveler: Traveler,
    val agency: TourAgency,
    val location: Location,
    val tourType: TourType,
    val name: String,
    val description: String?,
    val price: BigDecimal,
    val isDeleted: Boolean,
    val startDate: LocalDate,
    val endDate: LocalDate
)