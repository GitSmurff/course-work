package com.nikita.coursework.coursework.service

import com.nikita.coursework.coursework.entity.State
import com.nikita.coursework.coursework.repository.StateRepository
import org.springframework.data.crossstore.ChangeSetPersister
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

interface StateService {
    fun getById(id: Long): State
    fun create(createRequest: StateEntityCreateRequest): State
    fun update(state: State, updateRequest: StateEntityUpdateRequest): State
}

@Service
class StateServiceImpl(
    private val repository: StateRepository
): StateService {

    @Transactional(readOnly = true)
    override fun getById(id: Long): State {
        return repository.findById(id).orElseThrow { throw ChangeSetPersister.NotFoundException() }
    }

    @Transactional
    override fun create(createRequest: StateEntityCreateRequest): State {
        val state = State(
            name = createRequest.name,
            code = createRequest.code
        )

        return repository.save(state)
    }

    @Transactional
    override fun update(state: State, updateRequest: StateEntityUpdateRequest): State {
        state.id ?: throw IllegalArgumentException("State id can not be null!")

        state.apply {
            this.name = updateRequest.name
            this.code = updateRequest.code
        }

        return repository.save(state)
    }
}

data class StateEntityCreateRequest(
    val name: String,
    val code: String
)

data class StateEntityUpdateRequest(
    val name: String,
    val code: String
)