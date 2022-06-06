package com.nikita.coursework.controller

import com.nikita.coursework.endpoint.TourEndpoint
import com.nikita.coursework.endpoint.TourEntityCreatePayload
import com.nikita.coursework.endpoint.TourInfoDto
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1/api/tour")
class TourController(
    private val tourEndpoint: TourEndpoint
) {
    @GetMapping("/{tourId}")
    fun getById(@PathVariable tourId: Long): TourInfoDto {
        return tourEndpoint.getById(tourId)
    }

    @GetMapping("/all")
    fun getAll(): List<TourInfoDto> {
        return tourEndpoint.getAll()
    }

    @PostMapping
    fun create(@RequestBody createPayload: TourEntityCreatePayload): TourInfoDto {
        return tourEndpoint.create(createPayload)
    }

    @DeleteMapping("/{tourId}")
    fun softDelete(@PathVariable tourId: Long) {
        return tourEndpoint.softDelete(tourId)
    }
}