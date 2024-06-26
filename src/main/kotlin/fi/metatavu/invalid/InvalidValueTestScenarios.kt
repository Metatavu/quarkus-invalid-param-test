package fi.metatavu.invalid

import io.restassured.module.kotlin.extensions.Extract
import io.restassured.module.kotlin.extensions.Given
import io.restassured.module.kotlin.extensions.When
import org.junit.jupiter.api.Assertions.*
import org.slf4j.LoggerFactory
import io.restassured.http.ContentType

/**
 * Scenarios for invalid value test scenario builder
 */
class InvalidValueTestScenarios(private val scenarios: MutableList<InvalidValueTestScenario>) {

    private val logger = LoggerFactory.getLogger(InvalidValueTestScenarios::class.java)

    /**
     * Runs tests
     */
    fun test() {
        logger.debug("Executing test ${scenarios.size} scenarios")

        scenarios.forEach { scenario ->
            logger.debug("Executing test scenario ({}) {} with query: {}, path: {}, body: {}", scenario.method, scenario.path, scenario.queryParams, scenario.pathParams, scenario.body)

            Given {
                baseUri(scenario.basePath)
            } When {
                queryParams(scenario.queryParams)
                pathParams(scenario.pathParams)
                if (scenario.token != null) {
                    auth().preemptive().oauth2(scenario.token)
                }
                if (scenario.header != null) {
                    header(scenario.header.first, scenario.header.second)
                }
                if (scenario.body != null) {
                    body(scenario.body)
                    contentType(ContentType.JSON)
                }
                request(scenario.method, scenario.path)
            } Extract {
                val statusCode = statusCode()
                assertEquals(scenario.expectedStatus, statusCode, "Test scenario (${scenario.method}) ${scenario.path} with query: ${scenario.queryParams}, path: ${scenario.pathParams} failed. Expected status ${scenario.expectedStatus} does not match ${statusCode}")
            }
        }
    }

}