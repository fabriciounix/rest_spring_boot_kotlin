package br.com.fabriciounix.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus
import java.lang.*

@ResponseStatus(HttpStatus.NOT_FOUND)
class ResponseNotFoundException(exception: String?): RuntimeException(exception) {
}