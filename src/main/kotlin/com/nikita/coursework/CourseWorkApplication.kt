package com.nikita.coursework

import org.springframework.boot.autoconfigure.AutoConfigurationPackage
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@SpringBootApplication
@AutoConfigurationPackage
class CourseWorkApplication

fun main(args: Array<String>) {
    runApplication<CourseWorkApplication>(*args)
}