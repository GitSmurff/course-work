package com.nikita.coursework.annotation

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty

@ConditionalOnProperty(value = ["swagger.on"], havingValue = "true")
annotation class SwaggerOn()
