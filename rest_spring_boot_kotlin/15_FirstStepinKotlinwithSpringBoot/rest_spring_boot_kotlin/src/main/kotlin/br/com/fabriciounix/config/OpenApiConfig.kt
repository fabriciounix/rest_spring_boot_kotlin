package br.com.fabriciounix.config

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.info.License
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class OpenApiConfig {

    @Bean
    fun customOpenApi() : OpenAPI {
        return OpenAPI()
            .info(
                Info()
                    .title("Restful API com Spring Boot e Kotlin")
                    .version("v1")
                    .termsOfService("https://www.fabriciounix.com.br")
                    .license(
                        License().name("Apache 2.0")
                            .url("https://www.fabriciounix.com.br")
                    )
            )
    }
}