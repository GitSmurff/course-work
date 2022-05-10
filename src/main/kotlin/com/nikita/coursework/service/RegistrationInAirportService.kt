package com.nikita.coursework.coursework.service

import com.nikita.coursework.coursework.entity.Airport
import com.nikita.coursework.coursework.entity.RegistrationInAirport
import com.nikita.coursework.coursework.entity.RegistrationStatus
import com.nikita.coursework.coursework.entity.Traveler
import com.nikita.coursework.coursework.repository.RegistrationInAirportRepository
import com.querydsl.core.BooleanBuilder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate
import javax.persistence.*

interface RegistrationInAirportService {
//    fun findById(id: Long): RegistrationInAirport?
    fun create(createRequest: RegistrationInAirportEntityCreateRequest): RegistrationInAirport
    fun update(registration: RegistrationInAirport, updateRequest: RegistrationInAirportEntityUpdateRequest): RegistrationInAirport
    fun softDelete(registration: RegistrationInAirport)
}

@Service
class RegistrationInAirportServiceImpl(
    private val repository: RegistrationInAirportRepository
): RegistrationInAirportService {
//    @Transactional(readOnly = true)
//    override fun findById(id: Long): RegistrationInAirport? {
//        val booleanBuilder = BooleanBuilder()
//        booleanBuilder.and(QRegistrationInAirport.registrationInAirport.id.eq(id))
//        booleanBuilder.and(QRegistrationInAirport.registrationInAirport.idDeleted.eq(false))
//        return repository.findOne(booleanBuilder).orElseGet { null }
//    }

    @Transactional
    override fun create(createRequest: RegistrationInAirportEntityCreateRequest): RegistrationInAirport {
        val registration = RegistrationInAirport(
            traveler = createRequest.traveler,
            airport = createRequest.airport,
            arrivedDate = createRequest.arrivedDate,
            departedDate = createRequest.departedDate,
            status = createRequest.status,
            isDeleted = createRequest.isDeleted
        )

        return repository.save(registration)
    }

    @Transactional
    override fun update(registration: RegistrationInAirport, updateRequest: RegistrationInAirportEntityUpdateRequest): RegistrationInAirport {
        registration.id ?: throw IllegalArgumentException("RegistrationInAirport id can not be null!")

        registration.apply {
            this.arrivedDate = updateRequest.arrivedDate
            this.departedDate = updateRequest.departedDate
            this.status = updateRequest.status
        }

        return repository.save(registration)
    }

    @Transactional
    override fun softDelete(registration: RegistrationInAirport) {
        registration.id ?: throw IllegalArgumentException("RegistrationInAirport id can not be null!")

        registration.apply {
            this.isDeleted = true
        }

        repository.save(registration)
    }
}

data class RegistrationInAirportEntityCreateRequest(
    val traveler: Traveler,
    val airport: Airport,
    val arrivedDate: LocalDate,
    val departedDate: LocalDate,
    val status: RegistrationStatus,
    val isDeleted: Boolean
)

data class RegistrationInAirportEntityUpdateRequest(
    val arrivedDate: LocalDate,
    val departedDate: LocalDate,
    val status: RegistrationStatus
)