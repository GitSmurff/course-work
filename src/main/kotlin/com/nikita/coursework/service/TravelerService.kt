package com.nikita.coursework.coursework.service

import com.nikita.coursework.coursework.entity.Location
import com.nikita.coursework.coursework.entity.Traveler
import com.nikita.coursework.coursework.repository.TravelerRepository
import com.querydsl.core.BooleanBuilder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

interface TravelerService {
//    fun findById(id: Long): Traveler?
    fun create(createRequest: TravelerEntityCreateRequest): Traveler
    fun softDelete(traveler: Traveler)
}

@Service
class TravelerServiceImpl(
    private val repository: TravelerRepository
): TravelerService {

//    @Transactional(readOnly = true)
//    override fun findById(id: Long): Traveler? {
//        val booleanBuilder = BooleanBuilder()
//        booleanBuilder.and(QTraveler.traveler.id.eq(id))
//        booleanBuilder.and(QTraveler.traveler.idDeleted.eq(false))
//        return repository.findOne(booleanBuilder).orElseGet { null }
//    }

    @Transactional
    override fun create(createRequest: TravelerEntityCreateRequest): Traveler {
        val traveler = Traveler(
            firstName = createRequest.firstName,
                    lastName = createRequest.lastName,
                    middleName = createRequest.middleName,
                    phoneNumber = createRequest.phoneNumber,
                    location = createRequest.location,
                    isDeleted = createRequest.isDeleted
        )

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
    val phoneNumber: String?,
    val location: Location?,
    val isDeleted: Boolean
)