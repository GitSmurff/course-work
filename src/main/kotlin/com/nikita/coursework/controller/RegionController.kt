package com.nikita.coursework.controller

import com.nikita.coursework.endpoint.RegionEndpoint
import com.nikita.coursework.endpoint.RegionInfoDto
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/api/region")
class RegionController(
    private val regionEndpoint: RegionEndpoint
) {

    @GetMapping("/all")
    fun getAll(): List<RegionInfoDto>  {
        return regionEndpoint.getAll()
    }
}