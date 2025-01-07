package br.com.fabriciounix.repository

import br.com.fabriciounix.model.BooK
import org.springframework.data.jpa.repository.JpaRepository
//import java.awt.print.Book
import org.springframework.stereotype.Repository

@Repository
interface BookRepository: JpaRepository<BooK , Long>