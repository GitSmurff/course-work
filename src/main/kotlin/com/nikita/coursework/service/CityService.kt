package com.nikita.coursework.service

import com.nikita.coursework.entity.City
import com.nikita.coursework.entity.Country
import com.nikita.coursework.entity.Region
import com.nikita.coursework.reposiroty.CityRepository
import com.querydsl.core.BooleanBuilder
import org.springframework.data.crossstore.ChangeSetPersister
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

interface CityService {
    fun getById(id: Long): City
    fun getAll(): List<City>
    fun create(createRequest: CityEntityCreateRequest, country: Country, region: Region): City
    fun update(city: City, updateRequest: CityEntityUpdateRequest, country: Country, region: Region): City
}

@Service
class CityServiceImpl(
    private val repository: CityRepository
): CityService {

    @Transactional(readOnly = true)
    override fun getById(id: Long): City {
        return repository.findById(id).orElseThrow { throw ChangeSetPersister.NotFoundException() }
    }

    @Transactional(readOnly = true)
    override fun getAll(): List<City> {
        val booleanBuilder = BooleanBuilder()

        return repository.findAll(booleanBuilder).toList()
    }

    @Transactional
    override fun create(createRequest: CityEntityCreateRequest, country: Country, region: Region): City {
        val city = City(
            name = createRequest.name,
            country = country,
            region = region
        )

        return repository.save(city)
    }

    @Transactional
    override fun update(city: City, updateRequest: CityEntityUpdateRequest, country: Country, region: Region): City {
        city.id ?: throw IllegalArgumentException("City id can not be null!")

        city.apply {
            this.name = updateRequest.name
            this.country = country
            this.region = region
        }

        return repository.save(city)
    }
}

data class CityEntityCreateRequest(
    val name: String
)

data class CityEntityUpdateRequest(
    val name: String
)