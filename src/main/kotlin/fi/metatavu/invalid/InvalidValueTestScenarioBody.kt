package fi.metatavu.invalid

/**
 * Class for invalid body test scenarios
 *
 * @param values values to test
 * @param default default value
 * @param expectedStatus expected status code
 */
class InvalidValueTestScenarioBody(
    values: Collection<InvalidValueProvider>,
    expectedStatus: Int
) : InvalidValueTestScenarioBase("", values, emptyList(), null, expectedStatus)