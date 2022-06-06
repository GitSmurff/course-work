package com.nikita.coursework.controller

import com.nikita.coursework.endpoint.TourOperatorEndpoint
import com.nikita.coursework.endpoint.TourOperatorEntityCreatePayload
import com.nikita.coursework.endpoint.TourOperatorInfoDto
import com.nikita.coursework.service.TourOperatorEntityUpdateRequest
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1/api/tourOperator")
class TourOperatorController(
    private val tourOperatorEndpoint: TourOperatorEndpoint
) {

    @GetMapping("/{operatorId}")
    fun getById(@PathVariable operatorId: Long): TourOperatorInfoDto {
        return tourOperatorEndpoint.getById(operatorId)
    }

    @GetMapping("/all")
    fun getAll(): List<TourOperatorInfoDto> {
        return tourOperatorEndpoint.getAll()
    }

    @PostMapping
    fun create(@RequestBody createPayload: TourOperatorEntityCreatePayload): TourOperatorInfoDto {
        return tourOperatorEndpoint.create(createPayload)
    }

    @PutMapping("/{operatorId}")
    fun update(@PathVariable operatorId: Long, @RequestBody updateRequest: TourOperatorEntityUpdateRequest): TourOperatorInfoDto {
        return  tourOperatorEndpoint.update(operatorId, updateRequest)
    }

    @DeleteMapping("/{operatorId}")
    fun softDelete(@PathVariable operatorId: Long) {
        return tourOperatorEndpoint.softDelete(operatorId)
    }
}