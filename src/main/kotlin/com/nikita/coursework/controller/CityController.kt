package com.nikita.coursework.controller

import com.nikita.coursework.endpoint.CityEndpoint
import com.nikita.coursework.endpoint.CityEntityCreatePayload
import com.nikita.coursework.endpoint.CityEntityUpdatePayload
import com.nikita.coursework.endpoint.CityInfoDto
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1/api/city")
class CityController(
    private val cityEndpoint: CityEndpoint
) {
    @GetMapping("/{cityId}")
    fun getById(@PathVariable cityId: Long): CityInfoDto {
        return cityEndpoint.getById(cityId)
    }
    @GetMapping("/all")
    fun getAll(): List<CityInfoDto> {
        return cityEndpoint.getAll()
    }

    @PostMapping
    fun create(@RequestBody createPayload: CityEntityCreatePayload): CityInfoDto {
        return cityEndpoint.create(createPayload)
    }
    @PutMapping("/{cityId}")
    fun update(@PathVariable cityId: Long, @RequestBody updatePayload: CityEntityUpdatePayload): CityInfoDto {
        return  cityEndpoint.update(cityId, updatePayload)
    }
}