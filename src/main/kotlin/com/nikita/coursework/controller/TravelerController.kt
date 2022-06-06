package com.nikita.coursework.controller

import com.nikita.coursework.endpoint.TravelerEndpoint
import com.nikita.coursework.endpoint.TravelerEntityCreatePayload
import com.nikita.coursework.endpoint.TravelerInfoDto
import com.nikita.coursework.service.TravelerEntityCreateRequest
import com.nikita.coursework.service.TravelerEntityUpdateRequest
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/api/traveler")
class TravelerController(
    private val travelerEndpoint: TravelerEndpoint
) {
    @GetMapping("/{travelerId}")
    fun getById(@PathVariable travelerId: Long): TravelerInfoDto {
        return travelerEndpoint.getById(travelerId)
    }

    @GetMapping("/all")
    fun getAll(): List<TravelerInfoDto> {
        return travelerEndpoint.getAll()
    }

    @PostMapping
    fun create(@RequestBody createPayload: TravelerEntityCreatePayload): TravelerInfoDto {
        return travelerEndpoint.create(createPayload)
    }

    @PutMapping("/{travelerId}")
    fun update(@PathVariable travelerId: Long, @RequestBody updateRequest: TravelerEntityUpdateRequest): TravelerInfoDto {
        return travelerEndpoint.update(travelerId, updateRequest)
    }

    @DeleteMapping("/{travelerId}")
    fun softDelete(@PathVariable travelerId: Long) {
        return travelerEndpoint.softDelete(travelerId)
    }
}