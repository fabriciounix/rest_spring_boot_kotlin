package br.com.fabriciounix.services

import br.com.fabriciounix.controller.BookController
import br.com.fabriciounix.controller.PersonController
import br.com.fabriciounix.data.vo.v1.BookVO
import br.com.fabriciounix.data.vo.v1.PersonVO
import br.com.fabriciounix.exceptions.RequiredObjectisNullException
import br.com.fabriciounix.exceptions.ResponseNotFoundException
import br.com.fabriciounix.mapper.DozerMapper
import br.com.fabriciounix.model.BooK
import br.com.fabriciounix.model.Person
import br.com.fabriciounix.repository.BookRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo
import org.springframework.stereotype.Service
import java.awt.print.Book
import java.util.logging.Logger

@Service
class BookServices {

    @Autowired
    private lateinit var repository: BookRepository

    private  val  logger = Logger.getLogger(BookServices::class.java.name)

    fun findAll(): List<BookVO> {

        logger.info("Procurando todos os livros")

        val books = repository.findAll()

        val vos = DozerMapper.parseListObjects(books, BookVO::class.java)

        for (book in vos) {
            val withSelfRel = linkTo(BookController::class.java).slash(book.key).withSelfRel()
            book.add(withSelfRel)
        }
        return  vos
    }

    fun findById(id: Long): BookVO {

        logger.info("Procurando um livro com Id $id!")

        val book =  repository.findById(id)
            .orElseThrow { ResponseNotFoundException("Nada encontrado") }
        val bookVO: BookVO = DozerMapper.parseObject(book, BookVO::class.java)

        val withSelfRel = linkTo(PersonController::class.java).slash(bookVO.key).withSelfRel()
        bookVO.add(withSelfRel)
        return  bookVO
    }

    fun create(book: BookVO?): BookVO{
        if (book == null) throw RequiredObjectisNullException()
        logger.info("Creating one book with title ${book.title}!")
        var entity: BooK = DozerMapper.parseObject(book, BooK::class.java)
        val bookVO: BookVO = DozerMapper.parseObject(repository.save(entity), BookVO::class.java)
        val withSelfRel = linkTo(BookController::class.java).slash(bookVO.key).withSelfRel()
        bookVO.add(withSelfRel)
        return bookVO
    }

    fun update(book: BookVO?) : BookVO {
        if (book == null) throw RequiredObjectisNullException()
        logger.info("Atualizando um livro a com o id ${book.key}")
        val entity = repository.findById(book.key)
            .orElseThrow { ResponseNotFoundException("Nada encontrado")}

        entity.author = book.author
        entity.launchDate = book.launchDate
        entity.price = book.price
        entity.title = book.title
        val bookVO: BookVO = DozerMapper.parseObject(repository.save(entity), BookVO::class.java)

        val withSelfRel = linkTo(PersonController::class.java).slash(bookVO.key).withSelfRel()
        bookVO.add(withSelfRel)
        return  bookVO
    }

    fun delete(id: Long) {
        logger.info("Deletando um livro com o id $id")
        val entity=  repository.findById(id)
            .orElseThrow { ResponseNotFoundException("Nada encontrado")}
        repository.delete(entity)
    }
}