package com.nikita.coursework.service

import com.nikita.coursework.entity.QRegion
import com.nikita.coursework.entity.Region
import com.nikita.coursework.reposiroty.RegionRepository
import com.querydsl.core.BooleanBuilder
import org.springframework.data.crossstore.ChangeSetPersister
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

interface RegionService {
    fun getById(id: Long): Region
    fun getAll(): List<Region>
}

@Service
class RegionServiceImpl(
    private val repository: RegionRepository
): RegionService {
    @Transactional(readOnly = true)
    override fun getById(id: Long): Region {
        val booleanBuilder = BooleanBuilder()
        booleanBuilder.and(QRegion.region.id.eq(id))
        return repository.findOne(booleanBuilder).orElseThrow() { throw ChangeSetPersister.NotFoundException() }
    }

    @Transactional(readOnly = true)
    override fun getAll(): List<Region> {
        val booleanBuilder = BooleanBuilder()

        return repository.findAll(booleanBuilder).toList()
    }

}