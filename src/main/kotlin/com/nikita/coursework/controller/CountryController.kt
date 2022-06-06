package com.nikita.coursework.controller

import com.nikita.coursework.service.CountryEntityCreateRequest
import com.nikita.coursework.service.CountryEntityUpdateRequest
import com.nikita.coursework.endpoint.CountryEndpoint
import com.nikita.coursework.endpoint.CountryInfoDto
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/api/country")
class CountryController(
    private val countryEndpoint: CountryEndpoint
) {

    @GetMapping("/{stateId}")
    fun getById(@PathVariable stateId: Long): CountryInfoDto {
        return countryEndpoint.getById(stateId)
    }

    @GetMapping("/all")
    fun getAll(): List<CountryInfoDto> {
        return countryEndpoint.getAll()
    }

    @PostMapping
    fun create(@RequestBody createRequest: CountryEntityCreateRequest): CountryInfoDto {
        return countryEndpoint.create(createRequest)
    }

    @PutMapping("/{stateId}")
    fun update(@PathVariable stateId: Long, @RequestBody updateRequest: CountryEntityUpdateRequest): CountryInfoDto {
        return countryEndpoint.update(stateId, updateRequest)
    }
}