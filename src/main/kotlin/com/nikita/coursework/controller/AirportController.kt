package com.nikita.coursework.controller

import com.nikita.coursework.endpoint.AirportEndpoint
import com.nikita.coursework.endpoint.AirportEntityCreatePayload
import com.nikita.coursework.endpoint.AirportInfoDto
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1/api/airport")
class AirportController(
    private val airportEndpoint: AirportEndpoint
) {

    @GetMapping("/{airportId}")
    fun getById(@PathVariable airportId: Long): AirportInfoDto {
        return airportEndpoint.getById(airportId)
    }

    @GetMapping("/all")
    fun getAll(): List<AirportInfoDto> {
        return airportEndpoint.getAll()
    }

    @PostMapping
    fun create(@RequestBody createPayload: AirportEntityCreatePayload): AirportInfoDto {
        return airportEndpoint.create(createPayload)
    }

    @DeleteMapping("/{airportId}")
    fun softDelete(@PathVariable airportId: Long) {
        return airportEndpoint.softDelete(airportId)
    }
}