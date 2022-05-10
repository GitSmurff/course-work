package com.nikita.coursework.coursework.service

import com.nikita.coursework.coursework.entity.City
import com.nikita.coursework.coursework.entity.Location
import com.nikita.coursework.coursework.repository.LocationRepository
import org.springframework.data.crossstore.ChangeSetPersister
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.math.BigDecimal

interface LocationService {
    fun getById(id: Long): Location
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

    @Transactional
    override fun create(createRequest: LocationEntityCreateRequest): Location {
        val location = Location(
            city = createRequest.city,
            cityName = createRequest.cityName,
            stateShortCode = createRequest.stateShortCode,
            street = createRequest.street,
            zipCode = createRequest.zipCode,
            lat = createRequest.lat,
            lng = createRequest.lng
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
    val cityName: String,
    val stateShortCode: String?,
    val street: String,
    val zipCode: String,
    val lat: BigDecimal?,
    val lng: BigDecimal?
)