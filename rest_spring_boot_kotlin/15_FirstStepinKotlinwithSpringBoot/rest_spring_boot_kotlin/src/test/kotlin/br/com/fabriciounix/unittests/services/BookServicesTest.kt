package br.com.fabriciounix.unittests.services

import br.com.fabriciounix.exceptions.RequiredObjectisNullException
import br.com.fabriciounix.repository.BookRepository
import br.com.fabriciounix.repository.PersonRepository
import br.com.fabriciounix.services.BookServices
import br.com.fabriciounix.services.PersonServices
import br.com.fabriciounix.unittests.mapper.mocks.MockPerson
import br.com.fabriciounix.unittests.mockito.mocks.MockBook
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock

import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.junit.jupiter.MockitoExtension
import java.lang.Exception
import java.util.*


@ExtendWith(MockitoExtension::class)
 internal class BookServicesTest {

  private lateinit var inputObject: MockBook

  @InjectMocks
  private lateinit var service : BookServices

  @Mock
  private lateinit var repository: BookRepository

@BeforeEach
 fun setUpMock() {

  inputObject = MockBook()
  MockitoAnnotations.openMocks(this)
 }

@Test
 fun findById() {
  val book = inputObject.mockEntity(1)
  book.id = 1L
  `when`(repository.findById(1)).thenReturn(Optional.of(book))

  val result = service.findById(1)

 assertNotNull(result)
 assertNotNull(result.key)
 assertNotNull(result.links)
 assertTrue(result.links.toString().contains("</api/book/v1/1>;rel=\"self\""))
 assertEquals("Some Title1", result.title)
 assertEquals("Some Author1", result.author)
 assertEquals(25.0, result.price)
 }

@Test
 fun findAll() {

 val list = inputObject.mockEntityList()

 `when`(repository.findAll()).thenReturn(list)

 val books = service.findAll()

 assertNotNull(books)
 assertEquals(14, books.size)

 val bookOne = books[1]

 assertNotNull(bookOne)
 assertNotNull(bookOne.key)
 assertNotNull(bookOne.links)
 assertTrue(bookOne.links.toString().contains("</api/book/v1/1>;rel=\"self\""))
 assertEquals("Some Title1", bookOne.title)
 assertEquals("Some Author1", bookOne.author)
 assertEquals(25.0, bookOne.price)

 val bookFour = books[4]

 assertNotNull(bookFour)
 assertNotNull(bookFour.key)
 assertNotNull(bookFour.links)
 assertTrue(bookFour.links.toString().contains("</api/book/v1/4>;rel=\"self\""))
 assertEquals("Some Title4", bookFour.title)
 assertEquals("Some Author4", bookFour.author)
 assertEquals(25.0, bookFour.price)

 val bookSeven = books[7]

 assertNotNull(bookSeven)
 assertNotNull(bookSeven.key)
 assertNotNull(bookSeven.links)
 assertTrue(bookSeven.links.toString().contains("</api/book/v1/7>;rel=\"self\""))
 assertEquals("Some Title7", bookSeven.title)
 assertEquals("Some Author7", bookSeven.author)
 assertEquals(25.0, bookSeven.price)



 }

 @Test
 fun createWithNullPerson() {
  val exception: Exception = assertThrows(
   RequiredObjectisNullException::class.java
  ) {service.create(null)}

  val expectedMessage = "It is not allowed to persist a null object!"
  val actualMessage = exception.message
  assertTrue(actualMessage!!.contains(expectedMessage))
 }

@Test
 fun create() {

 val entity = inputObject.mockEntity(1)

 val persisted = entity.copy()
 persisted.id = 1

 `when`(repository.save(entity)).thenReturn(persisted)

 val vo = inputObject.mockVO(1)
 val result = service.create(vo)

 assertNotNull(result)
 assertNotNull(result.key)
 assertNotNull(result.links)
 assertTrue(result.links.toString().contains("</api/book/v1/1>;rel=\"self\""))
 assertEquals("Some Title1", result.title)
 assertEquals("Some Author1", result.author)
 assertEquals(25.0, result.price)

}

 @Test
 fun updateWithNullPerson() {
  val exception: Exception = assertThrows(
   RequiredObjectisNullException::class.java
  ) {service.update(null)}

  val expectedMessage = "It is not allowed to persist a null object!"
  val actualMessage = exception.message
  assertTrue(actualMessage!!.contains(expectedMessage))
 }

@Test
 fun update() {

 val entity = inputObject.mockEntity(1)

 val persisted = entity.copy()
 persisted.id = 1

 `when`(repository.findById(1)).thenReturn(Optional.of(entity))
 `when`(repository.save(entity)).thenReturn(persisted)

 val vo = inputObject.mockVO(1)
 val result = service.update(vo)

 assertNotNull(result)
 assertNotNull(result.key)
 assertNotNull(result.links)
 assertTrue(result.links.toString().contains("</api/book/v1/1>;rel=\"self\""))
 assertEquals("Some Title1", result.title)
 assertEquals("Some Author1", result.author)
 assertEquals(25.0, result.price)
 }

@Test
 fun delete() {

  val entity = inputObject.mockEntity(1)

  `when`(repository.findById(1)).thenReturn(Optional.of(entity))

  service.delete(1)

 }
}