package br.com.fabriciounix.services

import br.com.fabriciounix.model.Person
import org.springframework.stereotype.Service
import java.util.concurrent.atomic.AtomicLong
import java.util.logging.Logger
@Service
class PersonServices {

    private val counter: AtomicLong = AtomicLong()

    private val logger = Logger.getLogger(PersonServices::class.java.name)

    fun findById(id: Long): Person {

        logger.info("Procurando uma pessoa")

        val person = Person()
        person.id = counter.incrementAndGet()
        person.firstName = "Fabricio"
        person.lastName = "Longuim"
        person.address = "Pirangi - SP"
        person.gender = "Masculino"

        return person
    }

    fun findAll(): List<Person> {

        logger.info("Procurando todas pessoas")

        val persons: MutableList<Person> = ArrayList()
        for (i in 0 ..  7) {
            val person = mockPerson(i)
            persons.add(person)
        }



        return persons
    }

    private fun mockPerson(i: Int): Person {

        val person = Person()
        person.id = counter.incrementAndGet()
        person.firstName = "Person Name $i"
        person.lastName = "Last Name $i"
        person.address = "Some Address in Brazil"
        person.gender = "Masculino"

        return person
    }

    fun create(person: Person) = person

    fun update(person: Person) = person

    fun delete(id: Long) {
    }

}