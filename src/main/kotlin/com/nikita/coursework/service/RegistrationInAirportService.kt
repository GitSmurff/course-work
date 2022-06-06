package com.nikita.coursework.service

import com.nikita.coursework.entity.*
import com.nikita.coursework.reposiroty.RegistrationInAirportRepository
import com.querydsl.core.BooleanBuilder
import org.springframework.data.crossstore.ChangeSetPersister
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate

interface RegistrationInAirportService {
    fun getById(id: Long): RegistrationInAirport
    fun getAll(): List<RegistrationInAirport>
    fun create(createRequest: RegistrationInAirportEntityCreateRequest, traveler: Traveler, airport: Airport): RegistrationInAirport
    fun update(registration: RegistrationInAirport, updateRequest: RegistrationInAirportEntityUpdateRequest): RegistrationInAirport
    fun softDelete(registration: RegistrationInAirport)
}

@Service
class RegistrationInAirportServiceImpl(
    private val repository: RegistrationInAirportRepository
): RegistrationInAirportService {
    @Transactional(readOnly = true)
    override fun getById(id: Long): RegistrationInAirport {
        val booleanBuilder = BooleanBuilder()
        booleanBuilder.and(QRegistrationInAirport.registrationInAirport.id.eq(id))
        booleanBuilder.and(QRegistrationInAirport.registrationInAirport.isDeleted.eq(false))
        return repository.findOne(booleanBuilder).orElseThrow { throw ChangeSetPersister.NotFoundException() }
    }
    @Transactional(readOnly = true)
    override fun getAll(): List<RegistrationInAirport> {
        val booleanBuilder = BooleanBuilder()

        return repository.findAll(booleanBuilder).toList()
    }

    @Transactional
    override fun create(createRequest: RegistrationInAirportEntityCreateRequest, traveler: Traveler, airport: Airport): RegistrationInAirport {
        val registration = RegistrationInAirport(
            traveler = traveler,
            airport = airport,
            arrivedDate = createRequest.arrivedDate,
            departedDate = createRequest.departedDate,
            status = createRequest.status,
            isDeleted = false
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
    val arrivedDate: LocalDate,
    val departedDate: LocalDate,
    val status: RegistrationStatus
)

data class RegistrationInAirportEntityUpdateRequest(
    val arrivedDate: LocalDate,
    val departedDate: LocalDate,
    val status: RegistrationStatus
)