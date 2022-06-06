package com.nikita.coursework.service

import com.nikita.coursework.entity.QTravelerGeoPositionHistory
import com.nikita.coursework.entity.Traveler
import com.nikita.coursework.entity.TravelerGeoPositionHistory
import com.nikita.coursework.entity.TravelerGeoPositionStatusType
import com.nikita.coursework.reposiroty.TravelerGeoPositionHistoryRepository
import com.querydsl.core.BooleanBuilder
import org.springframework.data.crossstore.ChangeSetPersister
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.math.BigDecimal

interface TravelerGeoPositionHistoryService {
    fun getById(id: Long): TravelerGeoPositionHistory
    fun getAll(): List<TravelerGeoPositionHistory>
    fun create(createRequest: TravelerGeoPositionHistoryEntityCreateRequest, traveler: Traveler): TravelerGeoPositionHistory
    fun softDelete(travelerGeoPositionHistory: TravelerGeoPositionHistory)
}

@Service
class TravelerGeoPositionHistoryServiceImpl(
    private val repository: TravelerGeoPositionHistoryRepository
): TravelerGeoPositionHistoryService {

    @Transactional(readOnly = true)
    override fun getById(id: Long): TravelerGeoPositionHistory {
        val booleanBuilder = BooleanBuilder()
        booleanBuilder.and(QTravelerGeoPositionHistory.travelerGeoPositionHistory.id.eq(id))
        booleanBuilder.and(QTravelerGeoPositionHistory.travelerGeoPositionHistory.status.eq(TravelerGeoPositionStatusType.OK))
        return repository.findOne(booleanBuilder).orElseThrow { throw ChangeSetPersister.NotFoundException() }
    }

    @Transactional(readOnly = true)
    override fun getAll(): List<TravelerGeoPositionHistory> {
        val booleanBuilder = BooleanBuilder()

        return repository.findAll(booleanBuilder).toList()
    }

    @Transactional
    override fun create(createRequest: TravelerGeoPositionHistoryEntityCreateRequest, traveler: Traveler): TravelerGeoPositionHistory {
        val travelerGeoPositionHistory = TravelerGeoPositionHistory(
            traveler = traveler,
            lat = createRequest.lat,
            lng = createRequest.lng,
            status = TravelerGeoPositionStatusType.OK
        )

        return repository.save(travelerGeoPositionHistory)
    }

    @Transactional
    override fun softDelete(travelerGeoPositionHistory: TravelerGeoPositionHistory) {
        travelerGeoPositionHistory.id ?: throw IllegalArgumentException("TourAgency id can not be null!")

        travelerGeoPositionHistory.apply {
            this.status = TravelerGeoPositionStatusType.DELETE
        }

        repository.save(travelerGeoPositionHistory)
    }
}

data class TravelerGeoPositionHistoryEntityCreateRequest(
    val lat: BigDecimal,
    val lng: BigDecimal
)