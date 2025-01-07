package br.com.fabriciounix.services

import br.com.fabriciounix.controller.PersonController
import br.com.fabriciounix.data.vo.v1.PersonVO
import br.com.fabriciounix.exceptions.RequiredObjectisNullException
import br.com.fabriciounix.model.Person
import br.com.fabriciounix.repository.PersonRepository
import br.com.fabriciounix.exceptions.ResponseNotFoundException
import br.com.fabriciounix.mapper.DozerMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo
import org.springframework.stereotype.Service
import java.util.concurrent.atomic.AtomicLong
import java.util.logging.Logger
@Service
class PersonServices {

    @Autowired
    private lateinit var  repository: PersonRepository

    private val logger = Logger.getLogger(PersonServices::class.java.name)

    fun findById(id: Long): PersonVO {

        logger.info("Procurando uma pessoa com Id $id!")

        val person =  repository.findById(id)
            .orElseThrow { ResponseNotFoundException("Nada encontrado")}
        val personVO: PersonVO = DozerMapper.parseObject(person, PersonVO::class.java)

        val withSelfRel = linkTo(PersonController::class.java).slash(personVO.key).withSelfRel()
        personVO.add(withSelfRel)
        return  personVO
    }

    fun findAll(): List<PersonVO> {

        logger.info("Procurando todas pessoas")

        val persons = repository.findAll()

        val vos = DozerMapper.parseListObjects(persons, PersonVO::class.java)

        for (person in vos) {
            val withSelfRel = linkTo(PersonController::class.java).slash(person.key).withSelfRel()
            person.add(withSelfRel)
        }
        return  vos
    }

    fun create(person: PersonVO?): PersonVO {
        if (person == null) throw RequiredObjectisNullException()
        logger.info("Criando uma pessoa com o nome ${person.firstName}")
        val entity: Person =  DozerMapper.parseObject(person, Person::class.java)
        val personVO: PersonVO = DozerMapper.parseObject(repository.save(entity), PersonVO::class.java)

        val withSelfRel = linkTo(PersonController::class.java).slash(personVO.key).withSelfRel()
        personVO.add(withSelfRel)
        return  personVO
    }

    fun update(person: PersonVO?) : PersonVO {
        if (person == null) throw RequiredObjectisNullException()
        logger.info("Atualizando uma pessoa com o id ${person.key}")
        val entity = repository.findById(person.key)
            .orElseThrow { ResponseNotFoundException("Nada encontrado")}

        entity.firstName = person.firstName
        entity.lastName = person.lastName
        entity.address = person.address
        entity.gender = person.gender
        val personVO: PersonVO = DozerMapper.parseObject(repository.save(entity), PersonVO::class.java)

        val withSelfRel = linkTo(PersonController::class.java).slash(personVO.key).withSelfRel()
        personVO.add(withSelfRel)
        return  personVO
    }

    fun delete(id: Long) {
        logger.info("Deletando uma pessoa com o id $id")
        val entity=  repository.findById(id)
            .orElseThrow { ResponseNotFoundException("Nada encontrado")}
        repository.delete(entity)
    }

}