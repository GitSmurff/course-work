package com.nikita.coursework.service

import com.nikita.coursework.entity.Country
import com.nikita.coursework.reposiroty.CountryRepository
import com.querydsl.core.BooleanBuilder
import org.springframework.data.crossstore.ChangeSetPersister
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

interface CountryService {
    fun getById(id: Long): Country
    fun getAll(): List<Country>
    fun create(createRequest: CountryEntityCreateRequest): Country
    fun update(country: Country, updateRequest: CountryEntityUpdateRequest): Country
}

@Service
class CountryServiceImpl(
    private val repository: CountryRepository
): CountryService {

    @Transactional(readOnly = true)
    override fun getById(id: Long): Country {
        return repository.findById(id).orElseThrow { throw ChangeSetPersister.NotFoundException() }
    }
    @Transactional(readOnly = true)
    override fun getAll(): List<Country> {
        val booleanBuilder = BooleanBuilder()

        return repository.findAll(booleanBuilder).toList()
    }

    @Transactional
    override fun create(createRequest: CountryEntityCreateRequest): Country {
        val country = Country(
            name = createRequest.name,
            code = createRequest.code
        )

        return repository.save(country)
    }

    @Transactional
    override fun update(country: Country, updateRequest: CountryEntityUpdateRequest): Country {
        country.id ?: throw IllegalArgumentException("State id can not be null!")

        country.apply {
            this.name = updateRequest.name
            this.code = updateRequest.code
        }

        return repository.save(country)
    }
}

data class CountryEntityCreateRequest(
    val name: String,
    val code: String
)

data class CountryEntityUpdateRequest(
    val name: String,
    val code: String
)