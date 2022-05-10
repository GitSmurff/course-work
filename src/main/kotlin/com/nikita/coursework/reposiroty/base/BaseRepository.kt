package com.nikita.coursework.coursework.repository.base

import com.nikita.coursework.coursework.entity.base.BaseEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.querydsl.QuerydslPredicateExecutor
import org.springframework.data.repository.NoRepositoryBean

@NoRepositoryBean
interface BaseRepository<T : BaseEntity> : JpaRepository<T, Long>, QuerydslPredicateExecutor<T>