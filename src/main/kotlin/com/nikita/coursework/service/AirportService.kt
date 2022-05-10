package com.nikita.coursework.coursework.service

import com.nikita.coursework.coursework.entity.Airport
import com.nikita.coursework.coursework.entity.Location
import com.nikita.coursework.coursework.repository.AirportRepository
import com.querydsl.core.BooleanBuilder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

interface AirportService {
//    fun findById(id: Long): Airport?
    fun create(createRequest: AirportEntityCreateRequest): Airport
    fun softDelete(airport: Airport)
}

@Service
class AirportServiceImpl(
    private val repository: AirportRepository
): AirportService {

//    @Transactional(readOnly = true)
//    override fun findById(id: Long): Airport? {
//        val booleanBuilder = BooleanBuilder()
//        booleanBuilder.and(QAirport.airport.id.eq(id))
//        booleanBuilder.and(QAirport.airport.idDeleted.eq(false))
//        return repository.findOne(booleanBuilder).orElseGet { null }
//    }

    @Transactional
    override fun create(createRequest: AirportEntityCreateRequest): Airport {
        val airport = Airport(
            name = createRequest.name,
            location = createRequest.location,
            isDeleted = createRequest.isDeleted
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
    val name: String,
    val location: Location?,
    val isDeleted: Boolean
)