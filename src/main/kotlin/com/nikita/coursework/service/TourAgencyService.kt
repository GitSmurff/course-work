package com.nikita.coursework.service

import com.nikita.coursework.entity.Location
import com.nikita.coursework.entity.QTourAgency
import com.nikita.coursework.entity.TourAgency
import com.nikita.coursework.reposiroty.TourAgencyRepository
import com.querydsl.core.BooleanBuilder
import org.springframework.data.crossstore.ChangeSetPersister
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

interface TourAgencyService {
    fun getById(id: Long): TourAgency
    fun getAll(): List<TourAgency>
    fun create(createRequest: TourAgencyEntityCreateRequest, location: Location): TourAgency
    fun softDelete(tourAgency: TourAgency)
}

@Service
class TourAgencyServiceImpl(
    private val repository: TourAgencyRepository
): TourAgencyService {
    @Transactional(readOnly = true)
    override fun getById(id: Long): TourAgency {
        val booleanBuilder = BooleanBuilder()
        booleanBuilder.and(QTourAgency.tourAgency.id.eq(id))
        booleanBuilder.and(QTourAgency.tourAgency.isDeleted.eq(false))
        return repository.findOne(booleanBuilder).orElseThrow { throw ChangeSetPersister.NotFoundException() }
    }
    @Transactional(readOnly = true)
    override fun getAll(): List<TourAgency> {
        val booleanBuilder = BooleanBuilder()

        return repository.findAll(booleanBuilder).toList()
    }

    @Transactional
    override fun create(createRequest: TourAgencyEntityCreateRequest, location: Location): TourAgency {
        val tourAgency = TourAgency(
            name = createRequest.name,
            location = location,
            isDeleted = false
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
    val name: String
)