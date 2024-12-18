package br.com.fabriciounix.services

import br.com.fabriciounix.model.Person
import br.com.fabriciounix.repository.PersonRepository
import br.com.fabriciounix.exceptions.ResponseNotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException
import org.springframework.stereotype.Service
import java.util.concurrent.atomic.AtomicLong
import java.util.logging.Logger
@Service
class PersonServices {

    @Autowired
    private lateinit var  repository: PersonRepository

    private val logger = Logger.getLogger(PersonServices::class.java.name)

    fun findById(id: Long): Person {

        logger.info("Procurando uma pessoa")

        return repository.findById(id)
            .orElseThrow { ResponseNotFoundException("Nada encontrado")}
    }

    fun findAll(): List<Person> {

        logger.info("Procurando todas pessoas")

        return repository.findAll()
    }

    fun create(person: Person): Person {
        logger.info("Criando uma pessoa com o nome ${person.firstName}")
        return repository.save(person)
    }

    fun update(person: Person) : Person {
        logger.info("Atualizando uma pessoa com o id ${person.id}")
        val entity = repository.findById(person.id)
            .orElseThrow { ResponseNotFoundException("Nada encontrado")}

        entity.firstName = person.firstName
        entity.lastName = person.lastName
        entity.address = person.address
        entity.gender = person.gender
        return repository.save(entity)
    }

    fun delete(id: Long) {
        logger.info("Deletando uma pessoa com o id $id")
        val entity=  repository.findById(id)
            .orElseThrow { ResponseNotFoundException("Nada encontrado")}
        repository.delete(entity)
    }

}