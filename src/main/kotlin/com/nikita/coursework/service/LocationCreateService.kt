package com.nikita.coursework.service

import com.nikita.coursework.endpoint.LocationEntityCreatePayload
import com.nikita.coursework.entity.Location
import com.nikita.coursework.entity.LocationSourceType
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

interface LocationCreateService {
    fun createPayload(createPayload: LocationEntityCreatePayload, sourceType: LocationSourceType): Location
}

@Service
class LocationCreateServiceImpl(
    private val locationService: LocationService,
    private val cityService: CityService
): LocationCreateService{
    @Transactional
    override fun createPayload(createPayload: LocationEntityCreatePayload, sourceType: LocationSourceType): Location {
        val city = cityService.getById(createPayload.cityId)
        val request = LocationEntityCreateRequest(
                    city = city,
                    street = createPayload.street,
                    lat = createPayload.lat,
                    lng = createPayload.lng,
                    source = sourceType
        )
        return locationService.create(request)
        
    }

}