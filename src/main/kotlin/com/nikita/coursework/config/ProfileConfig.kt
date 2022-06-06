package com.nikita.coursework.config

import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.PropertySource

@Configuration
@PropertySource(
    ignoreResourceNotFound = false,
    value = ["classpath:application.properties"])
class ProfileConfig
