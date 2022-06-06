package com.nikita.coursework.endpoint

import com.nikita.coursework.service.RegionService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

interface RegionEndpoint {
    fun getAll(): List<RegionInfoDto>
}

@Service
class RegionEndpointImpl(
    private val regionService: RegionService
): RegionEndpoint {
    @Transactional(readOnly = true)
    override fun getAll(): List<RegionInfoDto> {
        val response = regionService.getAll()

        return response.map {
            RegionInfoDto(
                id = it.id!!,
                title = it.title,
                description = it.description
            )
        }
    }
}

data class RegionInfoDto(
    val id: Long,
    val title: String,
    val description: String?
)