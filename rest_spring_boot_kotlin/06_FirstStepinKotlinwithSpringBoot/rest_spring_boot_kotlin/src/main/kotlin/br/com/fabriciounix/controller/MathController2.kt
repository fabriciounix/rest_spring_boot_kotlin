package br.com.fabriciounix.controller

import br.com.fabriciounix.exceptions.UnsupportedMathOperationException
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.atomic.AtomicLong

@RestController
class MathController2 {

    val counter: AtomicLong = AtomicLong()

   // @RequestMapping(value = ["/sum/{numberOne}/{numberTwo}"])
    fun sum(@PathVariable(value="numberOne") numberOne: String?,
            @PathVariable(value="numberTwo") numberTwo: String?
    ): Double {
        if (!isNumeric(numberOne) || !isNumeric(numberTwo)) throw UnsupportedMathOperationException("Por favor digite um valor numérico")
        return convertToDouble(numberOne) + convertToDouble(numberTwo)
    }

    //@RequestMapping(value = ["/sub/{numberOne}/{numberTwo}"])
    fun sub(@PathVariable(value="numberOne") numberOne: String?,
            @PathVariable(value="numberTwo") numberTwo: String?
    ): Double {
        if (!isNumeric(numberOne) || !isNumeric(numberTwo)) throw UnsupportedMathOperationException("Por favor digite um valor numérico")
        return convertToDouble(numberOne) - convertToDouble(numberTwo)
    }

    //@RequestMapping(value = ["/div/{numberOne}/{numberTwo}"])
    fun div(@PathVariable(value="numberOne") numberOne: String?,
            @PathVariable(value="numberTwo") numberTwo: String?
    ): Double {
        if (!isNumeric(numberOne) || !isNumeric(numberTwo)) throw UnsupportedMathOperationException("Por favor digite um valor numérico")
        return convertToDouble(numberOne) / convertToDouble(numberTwo)
    }

    //@RequestMapping(value = ["/media/{numberOne}/{numberTwo}"])
    fun media(@PathVariable(value="numberOne") numberOne: String?,
              @PathVariable(value="numberTwo") numberTwo: String?
    ): Double {
        if (!isNumeric(numberOne) || !isNumeric(numberTwo)) throw UnsupportedMathOperationException("Por favor digite um valor numérico")
        return (convertToDouble(numberOne) + convertToDouble(numberTwo)) / 2
    }

    //@RequestMapping(value = ["/raiz/{numberOne}"])
    fun raiz(@PathVariable(value="numberOne") numberOne: String?

    ): Double {
        if (!isNumeric(numberOne) ) throw UnsupportedMathOperationException("Por favor digite um valor numérico")
        return Math.sqrt(convertToDouble(numberOne))
    }

    //@RequestMapping(value = ["/mult/{numberOne}/{numberTwo}"])
    fun mult(@PathVariable(value="numberOne") numberOne: String?,
             @PathVariable(value="numberTwo") numberTwo: String?
    ): Double {
        if (!isNumeric(numberOne) || !isNumeric(numberTwo)) throw UnsupportedMathOperationException("Por favor digite um valor numérico")
        return convertToDouble(numberOne) * convertToDouble(numberTwo)
    }

    private fun convertToDouble(strNumber: String?): Double {
        if(strNumber.isNullOrBlank()) return 0.0
        // BR 10,20 US 10.20
        val number = strNumber.replace(", ".toRegex(), replacement = ". ")
        return if (isNumeric(number)) number.toDouble() else 0.0
    }

    private fun isNumeric(strNumber: String?): Boolean {
        if(strNumber.isNullOrBlank()) return false;
        val number = strNumber.replace(", ".toRegex(), replacement = ". ")
        return  number.matches(regex = """[-+]?[0-9]*\.?[0-9]+""".toRegex())
    }
}