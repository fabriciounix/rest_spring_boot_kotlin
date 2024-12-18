package br.com.fabriciounix.controller

import br.com.fabriciounix.model.Person
import br.com.fabriciounix.services.PersonServices
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.atomic.AtomicLong

@RestController
@RequestMapping("/person")
class PersonController {

    val counter: AtomicLong = AtomicLong()

    @Autowired
    private lateinit var service: PersonServices
    // esso comando é igual a: var service: PersonServices = PersonServices()

    @RequestMapping(value = ["/{id}"], method = [RequestMethod.GET],
        produces = [MediaType.APPLICATION_JSON_VALUE])
    fun findById(@PathVariable(value="id") id: Long

    ): Person {

        return service.findById(id)
    }

    @RequestMapping(method = [RequestMethod.GET],
        produces = [MediaType.APPLICATION_JSON_VALUE])
    fun findAll(): List<Person> {

        return service.findAll()
    }

    @RequestMapping(method = [RequestMethod.POST],
        consumes = [MediaType.APPLICATION_JSON_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE])
    fun create(@RequestBody person: Person

    ): Person {

        return service.create(person)
    }

    @RequestMapping(method = [RequestMethod.PUT],
        consumes = [MediaType.APPLICATION_JSON_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE])
    fun update(@RequestBody person: Person

    ): Person {

        return service.update(person)
    }

    @RequestMapping(value = ["/{id}"], method = [RequestMethod.DELETE],
        produces = [MediaType.APPLICATION_JSON_VALUE])
    fun delete(@PathVariable(value="id") id: Long

    ): Person {

        service.delete(id)
        return TODO("Provide the return value")
    }



}