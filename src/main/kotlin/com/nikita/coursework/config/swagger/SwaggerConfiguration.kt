package com.nikita.coursework.config.swagger

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.ApiInfo
import springfox.documentation.service.Contact
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2

@Configuration
@EnableSwagger2
class SwaggerConfiguration : WebMvcConfigurer {

    @Bean
    fun productApi(): Docket {
        return Docket(DocumentationType.SWAGGER_2)
            .host("http://localhost:8080")
            .select()
            .apis(RequestHandlerSelectors.basePackage("com.nikita.coursework.controller"))
            .paths(PathSelectors.any())
            .build()
            .apiInfo(apiInfo())
    }

    private fun apiInfo(): ApiInfo {
        return ApiInfo(
            "Course-work API",
            "Some description of API.",
            "V1",
            "Terms of service",
            Contact("Nikita Course-work", "", "mrnick1998@mail.ru"),
            "Nikita Course-work",
            "https://localhost:8080",
            ArrayList()
        )
    }
}
