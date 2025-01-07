package br.com.fabriciounix.unittests.mockito.mocks

import br.com.fabriciounix.data.vo.v1.BookVO
import br.com.fabriciounix.model.BooK

class MockBook {

    fun mockEntityList(): ArrayList<BooK> {
        val books: ArrayList<BooK> = ArrayList<BooK>()
        for (i in 0..13) {
            books.add(mockEntity(i))
        }
        return books
    }

    fun mockVOList(): ArrayList<BookVO> {
        val books: ArrayList<BookVO> = ArrayList()
        for (i in 0..13) {
            books.add(mockVO(i))
        }
        return books
    }

    fun mockEntity(number: Int) = BooK(
        id = number.toLong(),
        author = "Some Author$number",
        price = 25.0,
        title = "Some Title$number"
    )

    fun mockVO(number: Int) = BookVO(
        key = number.toLong(),
        author = "Some Author$number",
        price = 25.0,
        title = "Some Title$number"
    )
}