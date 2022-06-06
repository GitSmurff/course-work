package com.nikita.coursework.controller

import com.nikita.coursework.service.RegistrationInAirportEntityUpdateRequest
import com.nikita.coursework.endpoint.RegistrationInAirportEndpoint
import com.nikita.coursework.endpoint.RegistrationInAirportEntityCreatePayload
import com.nikita.coursework.endpoint.RegistrationInAirportInfoDto
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/api/registration/airport")
class RegistrationInAirportController(
    private val registrationInAirportEndpoint: RegistrationInAirportEndpoint
) {

    @GetMapping("/{registrationId}")
    fun findById(@PathVariable registrationId: Long): RegistrationInAirportInfoDto {
        return registrationInAirportEndpoint.findById(registrationId)
    }

    @GetMapping("/all")
    fun getAll(): List<RegistrationInAirportInfoDto> {
        return registrationInAirportEndpoint.getAll()
    }

    @PostMapping
    fun create(@RequestBody createPayload: RegistrationInAirportEntityCreatePayload): RegistrationInAirportInfoDto {
        return registrationInAirportEndpoint.create(createPayload)
    }

    @PutMapping("/{registrationId}")
    fun update( @PathVariable registrationId: Long, @RequestBody updateRequest: RegistrationInAirportEntityUpdateRequest): RegistrationInAirportInfoDto {
        return registrationInAirportEndpoint.update(registrationId, updateRequest)
    }

    @DeleteMapping("/{registrationId}")
    fun softDelete(@PathVariable registrationId: Long) {
        return registrationInAirportEndpoint.softDelete(registrationId)
    }
}