package br.com.fabriciounix.controller

import br.com.fabriciounix.data.vo.v1.PersonVO
import br.com.fabriciounix.model.Person
import br.com.fabriciounix.services.PersonServices
import br.com.fabriciounix.config.WebConfig
import br.com.fabriciounix.utils.MediaType
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.ArraySchema
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.concurrent.atomic.AtomicLong

//@CrossOrigin
@RestController
@RequestMapping("/api/person/v1")
@Tag(name = "People", description = "Endpoints para gerenciar pessoas")
class PersonController {

    val counter: AtomicLong = AtomicLong()

    @Autowired
    private lateinit var service: PersonServices
    // esso comando é igual a: var service: PersonServices = PersonServices()

    @GetMapping(produces = [MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML])
    @Operation(summary = "Lista todas as pessoas", description = "Lista todas as pessoas",
        tags = ["People"],
        responses = [
            ApiResponse(
                description = "Success",
                responseCode = "200",
                content = [
                    Content(array = ArraySchema(schema = io.swagger.v3.oas.annotations.media.Schema(implementation = PersonVO::class)))
                ]
            ),
            ApiResponse(description = "No content", responseCode = "204",
                content = [
                    Content(schema = io.swagger.v3.oas.annotations.media.Schema(implementation = Unit::class))
                ]
                ),
            ApiResponse(description = "Bad Request", responseCode = "400", content = [
                Content(schema = Schema(implementation = Unit::class))
            ]),
            ApiResponse(description = "Unauthorized", responseCode = "401", content = [
                Content(schema = Schema(implementation = Unit::class))
            ]),
            ApiResponse(description = "Not Found", responseCode = "404", content = [
                Content(schema = Schema(implementation = Unit::class))
            ]),
            ApiResponse(description = "Internal Error", responseCode = "500", content = [
                Content(schema = Schema(implementation = Unit::class))
            ]),
        ]
        )
    fun findAll(): List<PersonVO> {
        return service.findAll()
    }

    @CrossOrigin(origins = ["http://localhost:8080"])
    @GetMapping(value = ["/{id}"],
        produces = [MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML])
    @Operation(summary = "Exibindo uma pessoa", description = "Exibindo uma pessoa",
        tags = ["People"],
        responses = [
            ApiResponse(
                description = "Success",
                responseCode = "200",
                content = [
                    Content(schema = io.swagger.v3.oas.annotations.media.Schema(implementation = PersonVO::class))
                ]
            ),
            ApiResponse(description = "No content", responseCode = "204",
                content = [
                    Content(schema = io.swagger.v3.oas.annotations.media.Schema(implementation = Unit::class))
                ]
            ),
            ApiResponse(description = "Bad Request", responseCode = "400", content = [
                Content(schema = Schema(implementation = Unit::class))
            ]),
            ApiResponse(description = "Unauthorized", responseCode = "401", content = [
                Content(schema = Schema(implementation = Unit::class))
            ]),
            ApiResponse(description = "Not Found", responseCode = "404", content = [
                Content(schema = Schema(implementation = Unit::class))
            ]),
            ApiResponse(description = "Internal Error", responseCode = "500", content = [
                Content(schema = Schema(implementation = Unit::class))
            ]),
        ]
    )
    fun findById(@PathVariable(value="id") id: Long): PersonVO {
        return service.findById(id)
    }

    @CrossOrigin(origins = ["http://localhost:8080", "https://www.fabriciounix.com.br"])
    @PostMapping(consumes = [MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML],
        produces = [MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML])
    @Operation(summary = "Criando uma pessoa", description = "Criando uma pessoa",
        tags = ["People"],
        responses = [
            ApiResponse(
                description = "Success",
                responseCode = "200",
                content = [
                    Content(schema = io.swagger.v3.oas.annotations.media.Schema(implementation = PersonVO::class))
                ]
            ),
            ApiResponse(description = "Bad Request", responseCode = "400", content = [
                Content(schema = Schema(implementation = Unit::class))
            ]),
            ApiResponse(description = "Unauthorized", responseCode = "401", content = [
                Content(schema = Schema(implementation = Unit::class))
            ]),
            ApiResponse(description = "Internal Error", responseCode = "500", content = [
                Content(schema = Schema(implementation = Unit::class))
            ]),
        ]
    )
    fun create(@RequestBody person: PersonVO): PersonVO {
        return service.create(person)

    }

    @PutMapping(consumes = [MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML],
        produces = [MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML])
    @Operation(summary = "Atualizando informações de  uma pessoa", description = "Atualizando informações de uma pessoa",
        tags = ["People"],
        responses = [
            ApiResponse(
                description = "Success",
                responseCode = "200",
                content = [
                    Content(schema = io.swagger.v3.oas.annotations.media.Schema(implementation = PersonVO::class))
                ]
            ),
            ApiResponse(description = "No content", responseCode = "204",
                content = [
                    Content(schema = io.swagger.v3.oas.annotations.media.Schema(implementation = Unit::class))
                ]
            ),
            ApiResponse(description = "Bad Request", responseCode = "400", content = [
                Content(schema = Schema(implementation = Unit::class))
            ]),
            ApiResponse(description = "Unauthorized", responseCode = "401", content = [
                Content(schema = Schema(implementation = Unit::class))
            ]),
            ApiResponse(description = "Not Found", responseCode = "404", content = [
                Content(schema = Schema(implementation = Unit::class))
            ]),
            ApiResponse(description = "Internal Error", responseCode = "500", content = [
                Content(schema = Schema(implementation = Unit::class))
            ]),
        ]
    )
    fun update(@RequestBody person: PersonVO): PersonVO {
        return service.update(person)
    }

    @DeleteMapping(value = ["/{id}"],
        produces = [MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML])
    @Operation(summary = "Deletando informações de  uma pessoa", description = "Deletando informações de uma pessoa",
        tags = ["People"],
        responses = [
            ApiResponse(description = "No content", responseCode = "204",
                content = [
                    Content(schema = io.swagger.v3.oas.annotations.media.Schema(implementation = Unit::class))
                ]
            ),
            ApiResponse(description = "Bad Request", responseCode = "400", content = [
                Content(schema = Schema(implementation = Unit::class))
            ]),
            ApiResponse(description = "Unauthorized", responseCode = "401", content = [
                Content(schema = Schema(implementation = Unit::class))
            ]),
            ApiResponse(description = "Not Found", responseCode = "404", content = [
                Content(schema = Schema(implementation = Unit::class))
            ]),
            ApiResponse(description = "Internal Error", responseCode = "500", content = [
                Content(schema = Schema(implementation = Unit::class))
            ]),
        ]
    )
    fun delete(@PathVariable(value="id") id: Long) : ResponseEntity<*> {
        service.delete(id)
        return ResponseEntity.noContent().build<Any>()
    }




}