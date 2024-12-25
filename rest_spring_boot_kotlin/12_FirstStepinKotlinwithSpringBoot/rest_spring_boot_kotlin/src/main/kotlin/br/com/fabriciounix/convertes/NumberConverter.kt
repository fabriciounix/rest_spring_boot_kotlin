package br.com.fabriciounix.convertes

object NumberConverter {

    fun convertToDouble(strNumber: String?): Double {
        if(strNumber.isNullOrBlank()) return 0.0
        // BR 10,20 US 10.20
        val number = strNumber.replace(", ".toRegex(), replacement = ". ")
        return if (isNumeric(number)) number.toDouble() else 0.0
    }

    fun isNumeric(strNumber: String?): Boolean {
        if(strNumber.isNullOrBlank()) return false;
        val number = strNumber.replace(", ".toRegex(), replacement = ". ")
        return  number.matches(regex = """[-+]?[0-9]*\.?[0-9]+""".toRegex())
    }
}