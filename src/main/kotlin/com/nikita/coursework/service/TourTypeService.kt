package com.nikita.coursework.coursework.service

import com.nikita.coursework.coursework.entity.TourAgency
import com.nikita.coursework.coursework.entity.TourType
import com.nikita.coursework.coursework.repository.TourTypeRepository
import org.springframework.data.crossstore.ChangeSetPersister
import org.springframework.stereotype.Service

interface TourTypeService {
    fun getById(id: Long): TourType
    fun create(createRequest: TourTypeEntityCreateRequest): TourType
    fun delete(tourType: TourType)
}

@Service
class TourTypeServiceImpl(
    private val repository:TourTypeRepository
): TourTypeService {
    override fun getById(id: Long): TourType {
        return repository.findById(id).orElseThrow { throw ChangeSetPersister.NotFoundException() }
    }

    override fun create(createRequest: TourTypeEntityCreateRequest): TourType {
        val tourType = TourType(
            agency = createRequest.agency,
            title = createRequest.title
        )
        return repository.save(tourType)
    }

    override fun delete(tourType: TourType) {
        tourType.id ?: throw IllegalArgumentException("TravelerPreferences id can not be null!")

        repository.delete(tourType)
    }
}

data class TourTypeEntityCreateRequest(
    val agency: TourAgency,
    val title: String
)