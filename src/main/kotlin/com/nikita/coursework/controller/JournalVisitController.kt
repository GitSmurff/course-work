package com.nikita.coursework.controller

import com.nikita.coursework.service.JournalVisitEntityUpdateRequest
import com.nikita.coursework.endpoint.JournalVisitEndpoint
import com.nikita.coursework.endpoint.JournalVisitEntityCreatePayload
import com.nikita.coursework.endpoint.JournalVisitInfoDto
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/api/journal")
class JournalVisitController(
    private val journalVisitEndpoint: JournalVisitEndpoint
) {

    @GetMapping("/{journalVisitId}")
    fun getById(@PathVariable journalVisitId: Long): JournalVisitInfoDto {
        return  journalVisitEndpoint.getById(journalVisitId)
    }

    @GetMapping("/all")
    fun getAll(): List<JournalVisitInfoDto> {
        return  journalVisitEndpoint.getAll()
    }

    @PostMapping
    fun create(@RequestBody createPayload: JournalVisitEntityCreatePayload): JournalVisitInfoDto {
        return journalVisitEndpoint.create(createPayload)
    }

    @PutMapping("/{journalVisitId}")
    fun update(@PathVariable journalVisitId: Long, @RequestBody updateRequest: JournalVisitEntityUpdateRequest): JournalVisitInfoDto {
        return journalVisitEndpoint.update(journalVisitId, updateRequest)
    }

    @DeleteMapping("/{journalVisitId}")
    fun softDelete(@PathVariable journalVisitId: Long) {
        return journalVisitEndpoint.softDelete(journalVisitId)
    }
}