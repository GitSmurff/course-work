package com.nikita.coursework.service

import com.nikita.coursework.entity.*
import com.nikita.coursework.reposiroty.TourRepository
import com.querydsl.core.BooleanBuilder
import org.springframework.data.crossstore.ChangeSetPersister
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.math.BigDecimal
import java.time.LocalDate

interface TourService {
    fun getById(id: Long): Tour
    fun getAll(): List<Tour>
    fun create(
        createRequest: TourEntityCreateRequest,
        location: Location,
        traveler: Traveler,
        agency: TourAgency
    ): Tour
    fun softDelete(tour: Tour)
}

@Service
class TourServiceImpl(
    private val repository: TourRepository
): TourService {
    @Transactional(readOnly = true)
    override fun getById(id: Long): Tour {
        val booleanBuilder = BooleanBuilder()
        booleanBuilder.and(QTour.tour.id.eq(id))
        booleanBuilder.and(QTour.tour.isDeleted.eq(false))
        return repository.findOne(booleanBuilder).orElseThrow { throw ChangeSetPersister.NotFoundException() }
    }
    @Transactional(readOnly = true)
    override fun getAll(): List<Tour> {
        val booleanBuilder = BooleanBuilder()
        booleanBuilder.and(QTour.tour.isDeleted.eq(false))

        return repository.findAll(booleanBuilder).toList()
    }

    @Transactional
    override fun create(
        createRequest: TourEntityCreateRequest, location: Location, traveler: Traveler,
        agency: TourAgency
    ): Tour {

        val tour = Tour(
            traveler = traveler,
            agency = agency,
            location = location,
            name = createRequest.name,
            description = createRequest.description,
            price = createRequest.price,
            isDeleted = false,
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
    val name: String,
    val description: String?,
    val price: BigDecimal,
    val startDate: LocalDate,
    val endDate: LocalDate
)