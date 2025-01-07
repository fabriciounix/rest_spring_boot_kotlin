package br.com.fabriciounix.controller

import br.com.fabriciounix.data.vo.v1.BookVO
import br.com.fabriciounix.data.vo.v1.PersonVO
import br.com.fabriciounix.services.BookServices
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

@RestController
@RequestMapping("/api/books/v1")
@Tag(name = "Livros", description = "Endpoints para gerenciar livros")
class BookController {

    @Autowired
    private lateinit var services: BookServices

    @GetMapping(produces = [MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML])
    @Operation(summary = "Lista todos os livros", description = "Lista todos os livros",
        tags = ["Books"],
        responses = [
            ApiResponse(
                description = "Success",
                responseCode = "200",
                content = [
                    Content(array = ArraySchema(schema = io.swagger.v3.oas.annotations.media.Schema(implementation = BookVO::class)))
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
    fun findAll(): List<BookVO> {
        return services.findAll()
    }

    @GetMapping(value = ["/{id}"],
        produces = [MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML])
    @Operation(summary = "Exibindo um livro", description = "Exibindo um livro",
        tags = ["Books"],
        responses = [
            ApiResponse(
                description = "Success",
                responseCode = "200",
                content = [
                    Content(schema = io.swagger.v3.oas.annotations.media.Schema(implementation = BookVO::class))
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
    fun findById(@PathVariable(value = "id") id: Long): BookVO {
        return services.findById(id)
    }

    @PostMapping(consumes = [MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML],
        produces = [MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML])
    @Operation(summary = "Criando um livro", description = "Criando um livro",
        tags = ["Books"],
        responses = [
            ApiResponse(
                description = "Success",
                responseCode = "200",
                content = [
                    Content(schema = io.swagger.v3.oas.annotations.media.Schema(implementation = BookVO::class))
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
    fun create(@RequestBody book: BookVO): BookVO{
        return services.create(book)
    }

    @PutMapping(consumes = [MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML],
        produces = [MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML])
    @Operation(summary = "Atualizando informações de um livro", description = "Atualizando informações de um livro",
        tags = ["Book"],
        responses = [
            ApiResponse(
                description = "Success",
                responseCode = "200",
                content = [
                    Content(schema = io.swagger.v3.oas.annotations.media.Schema(implementation = BookVO::class))
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
    fun update(@RequestBody book: BookVO): BookVO{
        return services.update(book)
    }

    @DeleteMapping(value = ["/{id}"],
        produces = [MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML])
    @Operation(summary = "Deletando um livro", description = "Deletando um livro",
        tags = ["Book"],
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
        services.delete(id)
        return ResponseEntity.noContent().build<Any>()
    }
}