package br.com.fabriciounix.services

import br.com.fabriciounix.data.vo.v1.PersonVO
import br.com.fabriciounix.model.Person
import br.com.fabriciounix.repository.PersonRepository
import br.com.fabriciounix.exceptions.ResponseNotFoundException
import br.com.fabriciounix.mapper.DozerMapper
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

    fun findById(id: Long): PersonVO {

        logger.info("Procurando uma pessoa")

        val person =  repository.findById(id)
            .orElseThrow { ResponseNotFoundException("Nada encontrado")}
        return  DozerMapper.parseObject(person, PersonVO::class.java)
    }

    fun findAll(): List<PersonVO> {

        logger.info("Procurando todas pessoas")

        val persons = repository.findAll()

        return  DozerMapper.parseListObjects(persons, PersonVO::class.java)
    }

    fun create(person: PersonVO): PersonVO {
        logger.info("Criando uma pessoa com o nome ${person.firstName}")
        val entity: Person =  DozerMapper.parseObject(person, Person::class.java)
        return  DozerMapper.parseObject(repository.save(entity), PersonVO::class.java)
    }

    fun update(person: PersonVO) : PersonVO {
        logger.info("Atualizando uma pessoa com o id ${person.id}")
        val entity = repository.findById(person.id)
            .orElseThrow { ResponseNotFoundException("Nada encontrado")}

        entity.firstName = person.firstName
        entity.lastName = person.lastName
        entity.address = person.address
        entity.gender = person.gender
        return  DozerMapper.parseObject(repository.save(entity), PersonVO::class.java)
    }

    fun delete(id: Long) {
        logger.info("Deletando uma pessoa com o id $id")
        val entity=  repository.findById(id)
            .orElseThrow { ResponseNotFoundException("Nada encontrado")}
        repository.delete(entity)
    }

}