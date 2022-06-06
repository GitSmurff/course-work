package com.nikita.coursework.service

import com.nikita.coursework.entity.Airport
import com.nikita.coursework.entity.Location
import com.nikita.coursework.entity.QAirport
import com.nikita.coursework.reposiroty.AirportRepository
import com.querydsl.core.BooleanBuilder
import org.springframework.data.crossstore.ChangeSetPersister
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

interface AirportService {
    fun getById(id: Long): Airport
    fun getAll(): List<Airport>
    fun create(createRequest: AirportEntityCreateRequest, location: Location): Airport
    fun softDelete(airport: Airport)
}

@Service
class AirportServiceImpl(
    private val repository: AirportRepository
): AirportService {

    @Transactional(readOnly = true)
    override fun getById(id: Long): Airport {
        val booleanBuilder = BooleanBuilder()
        booleanBuilder.and(QAirport.airport.id.eq(id))
        booleanBuilder.and(QAirport.airport.isDeleted.eq(false))
        return repository.findOne(booleanBuilder).orElseThrow() { throw ChangeSetPersister.NotFoundException() }
    }

    @Transactional(readOnly = true)
    override fun getAll(): List<Airport> {
        val booleanBuilder = BooleanBuilder()
        booleanBuilder.and(QAirport.airport.isDeleted.eq(false))

        return repository.findAll(booleanBuilder).toList()
    }

    @Transactional
    override fun create(createRequest: AirportEntityCreateRequest, location: Location): Airport {
        val airport = Airport(
            name = createRequest.name,
            location = location,
            isDeleted = false
        )
        return repository.save(airport)
    }

    @Transactional
    override fun softDelete(airport: Airport) {
        airport.id ?: throw IllegalArgumentException("Airport id can not be null!")

        airport.apply {
            this.isDeleted = true
        }

        repository.save(airport)
    }
}

data class AirportEntityCreateRequest(
    val name: String
)