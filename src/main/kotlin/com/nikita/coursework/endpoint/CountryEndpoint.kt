package com.nikita.coursework.endpoint

import com.nikita.coursework.service.CountryEntityCreateRequest
import com.nikita.coursework.service.CountryEntityUpdateRequest
import com.nikita.coursework.service.CountryService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

interface CountryEndpoint {
    fun getById(countryId: Long): CountryInfoDto
    fun getAll(): List<CountryInfoDto>
    fun create(createRequest: CountryEntityCreateRequest): CountryInfoDto
    fun update(countryId: Long, updateRequest: CountryEntityUpdateRequest): CountryInfoDto
}

@Service
class CountryEndpointImpl(
    private val countryService: CountryService
): CountryEndpoint {

    @Transactional(readOnly = true)
    override fun getById(countryId: Long): CountryInfoDto {
        val response = countryService.getById(countryId)

        return CountryInfoDto(
            id = response.id!!,
            name = response.name,
            code = response.code
        )
    }

    @Transactional(readOnly = true)
    override fun getAll(): List<CountryInfoDto> {
        val response = countryService.getAll()

        return response.map {
            CountryInfoDto(
                id = it.id!!,
                name = it.name,
                code = it.code
            )
        }
    }

    @Transactional
    override fun create(createRequest: CountryEntityCreateRequest): CountryInfoDto {
        val response = countryService.create(createRequest)

        return CountryInfoDto(
            id = response.id!!,
            name = response.name,
            code = response.code
        )
    }

    @Transactional
    override fun update(countryId: Long, updateRequest: CountryEntityUpdateRequest): CountryInfoDto {
        val state = countryService.getById(countryId)
        val response = countryService.update(state, updateRequest)

        return CountryInfoDto(
            id = response.id!!,
            name = response.name,
            code = response.code
        )
    }

}

data class CountryInfoDto(
    val id: Long,
    val name: String,
    val code: String
)