package com.thopham.projects.research.apiservice.apidocument

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.ApiInfo
import springfox.documentation.service.Contact
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket

@Configuration
class ApiDocument {
    @Bean
    fun docket() : Docket{
        return Docket(DocumentationType.SWAGGER_2)
                .apiInfo(
                        ApiInfo(
                                "VRU Tracker APIs",
                                "No paint, No gaint.",
                                "1.0.0-SNAPSHOT",
                                "None",
                                Contact(
                                        "Thọ Phạm",
                                        "http://xnxx.com",
                                        "thopvna@gmail.com"
                                ),
                                "None",
                                "None",
                                listOf()
                        )
                )
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.thopham.projects.research.apiservice.controllers"))
                .paths(PathSelectors.any())
                .build()
    }
}