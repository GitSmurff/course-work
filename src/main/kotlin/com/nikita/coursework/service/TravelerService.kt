package com.nikita.coursework.service

import com.nikita.coursework.entity.Location
import com.nikita.coursework.entity.QTraveler
import com.nikita.coursework.entity.Traveler
import com.nikita.coursework.reposiroty.TravelerRepository
import com.querydsl.core.BooleanBuilder
import org.springframework.data.crossstore.ChangeSetPersister
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

interface TravelerService {
    fun getById(id: Long): Traveler
    fun getAll(): List<Traveler>
    fun create(createRequest: TravelerEntityCreateRequest, location: Location): Traveler
    fun update(traveler: Traveler, updateRequest: TravelerEntityUpdateRequest): Traveler
    fun softDelete(traveler: Traveler)
}

@Service
class TravelerServiceImpl(
    private val repository: TravelerRepository
): TravelerService {

    @Transactional(readOnly = true)
    override fun getById(id: Long): Traveler {

        val booleanBuilder = BooleanBuilder()
        booleanBuilder.and(QTraveler.traveler.id.eq(id))
        booleanBuilder.and(QTraveler.traveler.isDeleted.eq(false))
        return repository.findOne(booleanBuilder).orElseThrow() { throw ChangeSetPersister.NotFoundException() }
    }
    @Transactional(readOnly = true)
    override fun getAll(): List<Traveler> {
        val booleanBuilder = BooleanBuilder()

        return repository.findAll(booleanBuilder).toList()
    }

    @Transactional
    override fun create(createRequest: TravelerEntityCreateRequest, location: Location): Traveler {
        val traveler = Traveler(
            firstName = createRequest.firstName,
                    lastName = createRequest.lastName,
                    middleName = createRequest.middleName,
                    phoneNumber = createRequest.phoneNumber,
                    location = location,
                    isDeleted = false
        )

        return repository.save(traveler)
    }
    @Transactional
    override fun update(traveler: Traveler, updateRequest: TravelerEntityUpdateRequest): Traveler {
        traveler.id ?: throw IllegalArgumentException("Traveler id can not be null!")

        traveler.apply {
            this.firstName = updateRequest.firstName
            this.lastName = updateRequest.lastName
            this.middleName = updateRequest.middleName
        }

        return repository.save(traveler)
    }

    @Transactional
    override fun softDelete(traveler: Traveler) {
        traveler.id ?: throw IllegalArgumentException("Traveler id can not be null!")

        traveler.apply {
            this.isDeleted = true
        }

        repository.save(traveler)
    }

}

data class TravelerEntityCreateRequest(
    val firstName: String,
    val lastName: String,
    val middleName: String,
    val phoneNumber: String?
)

data class TravelerEntityUpdateRequest(
    val firstName: String,
    val lastName: String,
    val middleName: String
)