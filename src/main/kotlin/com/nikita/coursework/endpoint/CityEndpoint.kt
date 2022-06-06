package com.nikita.coursework.endpoint

import com.nikita.coursework.service.*
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

interface CityEndpoint {
    fun getById(cityId: Long): CityInfoDto
    fun getAll(): List<CityInfoDto>
    fun create(createPayload: CityEntityCreatePayload): CityInfoDto
    fun update(cityId: Long, updatePayload: CityEntityUpdatePayload): CityInfoDto
}

@Service
class CityEndpointImpl(
    private val cityService: CityService,
    private val countryService: CountryService,
    private val regionService: RegionService
): CityEndpoint {

    @Transactional(readOnly = true)
    override fun getById(cityId: Long): CityInfoDto {
        val response = cityService.getById(cityId)

        return CityInfoDto(
            id = response.id!!,
            name = response.name,
            country = CountryInfoDto(
                id = response.country.id!!,
                name = response.country.name,
                code = response.country.code
            )
        )
    }

    @Transactional(readOnly = true)
    override fun getAll(): List<CityInfoDto> {
        val response = cityService.getAll()

        return response.map {
            CityInfoDto(
                id = it.id!!,
                name = it.name,
                country = CountryInfoDto(
                    id = it.country.id!!,
                    name = it.country.name,
                    code = it.country.code
                )
            )
        }
    }

    @Transactional
    override fun create(createPayload: CityEntityCreatePayload): CityInfoDto {
        val country = countryService.getById(createPayload.countryId)
        val region = regionService.getById(createPayload.regionId)
        val response = cityService.create(createPayload.city, country, region)

        return CityInfoDto(
            id = response.id!!,
            name = response.name,
            country = CountryInfoDto(
                id = response.country.id!!,
                name = response.country.name,
                code = response.country.code
            )
        )
    }

    @Transactional
    override fun update(cityId: Long, updatePayload: CityEntityUpdatePayload): CityInfoDto {
        val city = cityService.getById(id = cityId)
        val country = countryService.getById(updatePayload.countryId)
        val region = regionService.getById(updatePayload.regionId)
        val response = cityService.update(city, updatePayload.city, country, region)

        return CityInfoDto(
            id = response.id!!,
            name = response.name,
            country = CountryInfoDto(
                id = response.country.id!!,
                name = response.country.name,
                code = response.country.code
            )
        )
    }
}

data class CityInfoDto(
    val id: Long,
    val name: String,
    val country: CountryInfoDto
)

data class CityEntityCreatePayload(
    val city: CityEntityCreateRequest,
    val countryId: Long,
    val regionId: Long
)

data class CityEntityUpdatePayload(
    val city: CityEntityUpdateRequest,
    val countryId: Long,
    val regionId: Long
)