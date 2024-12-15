package br.com.fabriciounix

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class RestSpringBootKotlinApplication

fun main(args: Array<String>) {
	runApplication<RestSpringBootKotlinApplication>(*args)
}
