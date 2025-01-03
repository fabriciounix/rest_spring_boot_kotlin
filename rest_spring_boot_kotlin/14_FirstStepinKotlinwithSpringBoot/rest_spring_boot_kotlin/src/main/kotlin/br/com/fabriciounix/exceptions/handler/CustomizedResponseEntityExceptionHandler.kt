package br.com.fabriciounix.exceptions.handler

import br.com.fabriciounix.exceptions.ExceptionResponse
import br.com.fabriciounix.exceptions.RequiredObjectisNullException
import br.com.fabriciounix.exceptions.ResponseNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.lang.Exception
import java.util.*

@ControllerAdvice
@RestController
class CustomizedResponseEntityExceptionHandler : ResponseEntityExceptionHandler(){

    @ExceptionHandler(Exception::class)
    fun handleAllExceptions(ex: Exception, request: WebRequest): ResponseEntity<ExceptionResponse> {
        val exceptioResponse = ExceptionResponse(
            Date(),
            ex.message,
            request.getDescription(false)
        )
        return ResponseEntity<ExceptionResponse>(exceptioResponse, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(ResponseNotFoundException::class)
    fun handleResponseNotFoundExceptions(ex: Exception, request: WebRequest): ResponseEntity<ExceptionResponse> {
        val exceptioResponse = ExceptionResponse(
            Date(),
            ex.message,
            request.getDescription(false)
        )
        return ResponseEntity<ExceptionResponse>(exceptioResponse, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(RequiredObjectisNullException::class)
    fun handleBadRequestExeptions(ex: Exception, request: WebRequest): ResponseEntity<ExceptionResponse> {
        val exceptioResponse = ExceptionResponse(
            Date(),
            ex.message,
            request.getDescription(false)
        )
        return ResponseEntity<ExceptionResponse>(exceptioResponse, HttpStatus.NOT_FOUND)
    }
}