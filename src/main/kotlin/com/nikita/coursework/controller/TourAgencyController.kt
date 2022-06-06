package com.nikita.coursework.controller

import com.nikita.coursework.endpoint.TourAgencyEndpoint
import com.nikita.coursework.endpoint.TourAgencyEntityCreatePayload
import com.nikita.coursework.endpoint.TourAgencyInfoDto
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1/api/tourAgency")
class TourAgencyController(
    private val tourAgencyEndpoint: TourAgencyEndpoint
) {

    @GetMapping("/{agencyId}")
    fun getById(@PathVariable agencyId: Long): TourAgencyInfoDto {
        return tourAgencyEndpoint.getById(agencyId)
    }

    @GetMapping("/all")
    fun getAll(): List<TourAgencyInfoDto> {
        return tourAgencyEndpoint.getAll()
    }

    @PostMapping
    fun create(@RequestBody createPayload: TourAgencyEntityCreatePayload): TourAgencyInfoDto {
        return tourAgencyEndpoint.create(createPayload)
    }

    @DeleteMapping("/{agencyId}")
    fun softDelete(@PathVariable agencyId: Long) {
        return tourAgencyEndpoint.softDelete(agencyId)
    }
}