package com.nikita.coursework.service

import com.nikita.coursework.entity.City
import com.nikita.coursework.entity.Location
import com.nikita.coursework.entity.LocationSourceType
import com.nikita.coursework.reposiroty.LocationRepository
import com.querydsl.core.BooleanBuilder
import org.springframework.data.crossstore.ChangeSetPersister
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.math.BigDecimal

interface LocationService {
    fun getById(id: Long): Location
    fun getAll(): List<Location>
    fun create(createRequest: LocationEntityCreateRequest): Location
    fun delete(location: Location)
}

@Service
class LocationServiceImpl(
    private val repository: LocationRepository
): LocationService {

    @Transactional(readOnly = true)
    override fun getById(id: Long): Location {
        return repository.findById(id).orElseThrow { throw ChangeSetPersister.NotFoundException() }
    }
    @Transactional(readOnly = true)
    override fun getAll(): List<Location> {
        val booleanBuilder = BooleanBuilder()

        return repository.findAll(booleanBuilder).toList()
    }

    @Transactional
    override fun create(createRequest: LocationEntityCreateRequest): Location {
        val location = Location(
            city = createRequest.city,
            street = createRequest.street,
            lat = createRequest.lat,
            lng = createRequest.lng,
            source = createRequest.source
        )

        return repository.save(location)
    }

    @Transactional
    override fun delete(location: Location) {
        location.id ?: throw IllegalArgumentException("Location id can not be null!")

        repository.delete(location)
    }
}

data class LocationEntityCreateRequest(
    val city: City,
    val street: String?,
    val lat: BigDecimal,
    val lng: BigDecimal,
    val source: LocationSourceType
)