package com.nikita.coursework.controller

import com.nikita.coursework.endpoint.TravelerPreferencesEndpoint
import com.nikita.coursework.endpoint.TravelerPreferencesEntityCreatePayload
import com.nikita.coursework.endpoint.TravelerPreferencesInfoDto
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1/api/traveler/preferences")
class TravelerPreferencesController(
    private val travelerPreferencesEndpoint: TravelerPreferencesEndpoint
) {

    @GetMapping("/{preferenceId}")
    fun getById(@PathVariable preferenceId: Long): TravelerPreferencesInfoDto {
        return travelerPreferencesEndpoint.getById(preferenceId)
    }

    @GetMapping("/all")
    fun getAll(): List<TravelerPreferencesInfoDto> {
        return travelerPreferencesEndpoint.getAll()
    }

    @PostMapping
    fun create(@RequestBody createPayload: TravelerPreferencesEntityCreatePayload): TravelerPreferencesInfoDto {
        return travelerPreferencesEndpoint.create(createPayload)
    }

    @DeleteMapping("/{preferenceId}")
    fun delete(@PathVariable preferenceId: Long) {
        return travelerPreferencesEndpoint.delete(preferenceId)
    }
}