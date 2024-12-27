package br.com.fabriciounix.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus
import java.lang.*

@ResponseStatus(HttpStatus.NOT_FOUND)
class RequiredObjectisNullException: RuntimeException {

    constructor(): super("Não é permitido perssistir um objeto nulo")
    constructor(exception: String?): super(exception)
}