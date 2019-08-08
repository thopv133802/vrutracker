package com.thopham.projects.research.apiservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import springfox.documentation.swagger2.annotations.EnableSwagger2

@SpringBootApplication
@EnableSwagger2
class ApiServiceApplication {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            runApplication<ApiServiceApplication>(*args)
        }
    }
}

