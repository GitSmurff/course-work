package com.nikita.coursework.controller

import com.nikita.coursework.endpoint.TravelerGeoPositionEntityCreatePayload
import com.nikita.coursework.endpoint.TravelerGeoPositionHistoryEndpoint
import com.nikita.coursework.endpoint.TravelerGeoPositionHistoryInfoDto
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1/api/TravelerGeoPositionHistory")
class TravelerGeoPositionHistoryController(
    private val travelerGeoPositionHistoryEndpoint: TravelerGeoPositionHistoryEndpoint
) {
    @GetMapping("/{travelerGeoPositionId}")
    fun getById(@PathVariable travelerGeoPositionId: Long): TravelerGeoPositionHistoryInfoDto {
        return travelerGeoPositionHistoryEndpoint.getById(travelerGeoPositionId)
    }

    @GetMapping("/all")
    fun getAll(): List<TravelerGeoPositionHistoryInfoDto> {
        return travelerGeoPositionHistoryEndpoint.getAll()
    }

    @PostMapping
    fun create(createPayload: TravelerGeoPositionEntityCreatePayload): TravelerGeoPositionHistoryInfoDto {
        return travelerGeoPositionHistoryEndpoint.create(createPayload)
    }

    @DeleteMapping("/{travelerGeoPositionId}")
    fun softDelete(@PathVariable travelerGeoPositionId: Long) {
        return travelerGeoPositionHistoryEndpoint.softDelete(travelerGeoPositionId)
    }
}