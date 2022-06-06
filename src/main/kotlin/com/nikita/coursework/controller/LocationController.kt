package com.nikita.coursework.controller

import com.nikita.coursework.endpoint.LocationEndpoint
import com.nikita.coursework.endpoint.LocationInfoDto
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1/api/location")
class LocationController(
    private val locationEndpoint: LocationEndpoint
) {
    @GetMapping("/{locationId}")
    fun getById(@PathVariable locationId: Long): LocationInfoDto {
        return locationEndpoint.getById(locationId)
    }

    @GetMapping("/all")
    fun getAll(): List<LocationInfoDto> {
        return locationEndpoint.getAll()
    }

//    @PostMapping
//    fun create(@RequestBody createRequest: LocationEntityCreateRequest): LocationInfoDto {
//        return locationEndpoint.create(createRequest)
//    }

    @DeleteMapping("/{locationId}")
    fun delete(@PathVariable locationId: Long) {
        return locationEndpoint.delete(locationId)
    }
}