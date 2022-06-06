package com.nikita.coursework.endpoint

import com.nikita.coursework.entity.LocationSourceType
import com.nikita.coursework.service.LocationCreateService
import com.nikita.coursework.service.LocationService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.math.BigDecimal

interface LocationEndpoint {
    fun getById(locationId: Long): LocationInfoDto
    fun getAll(): List<LocationInfoDto>
//    fun create(createRequest: LocationEntityCreatePayload): LocationInfoDto
    fun delete(locationId: Long)
}

@Service
class LocationEndpointImpl(
    private val locationService: LocationService,
    private val locationCreateService: LocationCreateService
): LocationEndpoint {
    @Transactional(readOnly = true)
    override fun getById(locationId: Long): LocationInfoDto {
        val response = locationService.getById(locationId)

        return LocationInfoDto(
            id = response.id!!,
            cityId = response.city.id!!,
            cityName = response.city.name,
            countryId = response.city.country.id!!,
            countryName = response.city.country.name,
            countryCode = response.city.country.code,
            street = response.street,
            lat = response.lat,
            lng = response.lng,
            source = response.source
        )
    }

    @Transactional(readOnly = true)
    override fun getAll(): List<LocationInfoDto> {
        val response = locationService.getAll()

        return response.map {
            LocationInfoDto(
                id = it.id!!,
                cityId = it.city.id!!,
                cityName = it.city.name,
                countryId = it.city.country.id!!,
                countryName = it.city.country.name,
                countryCode = it.city.country.code,
                street = it.street,
                lat = it.lat,
                lng = it.lng,
                source = it.source
            )
        }
    }

//    @Transactional
//    override fun create(createRequest: LocationEntityCreatePayload): LocationInfoDto {
//        val response = locationCreateService.createPayload(createRequest, )
//
//        return LocationInfoDto(
//            id = response.id!!,
//            cityId = response.city.id!!,
//            cityName = response.city.name,
//            countryId = response.city.country.id!!,
//            countryName = response.city.country.name,
//            countryCode = response.city.country.code,
//            street = response.street,
//            lat = response.lat,
//            lng = response.lng,
//            source = response.source
//        )
//    }

    @Transactional
    override fun delete(locationId: Long) {
        val location = locationService.getById(locationId)
        locationService.delete(location)
    }

}

data class LocationInfoDto(
    val id: Long,
    val cityId: Long,
    val cityName: String,
    val countryId: Long,
    val countryName: String,
    val countryCode: String,
    val street: String?,
    val lat: BigDecimal,
    val lng: BigDecimal,
    val source: LocationSourceType
)

data class LocationEntityCreatePayload(
    val cityId: Long,
    val street: String?,
    val lat: BigDecimal,
    val lng: BigDecimal
)