package br.com.fabriciounix

import br.com.fabriciounix.integrationtest.TestConfigs
import br.com.fabriciounix.integrationtest.testcontainer.AbstractIntegrationTest
import io.restassured.RestAssured.given
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class RestSpringBootKotlinApplicationTests() : AbstractIntegrationTest() {

	@Test
	fun shouldDisplaySwaggerUIPage() {
		val content = given()
			.basePath("/swagger-ui/index.html")
			.port(TestConfigs.SERVER_PORT)
				.`when`()
			.get()
			.then()
				.statusCode(200)
			.extract()
			.body()
				.asString()
		Assertions.assertTrue(content.contains("Swagger UI"))

	}

}
