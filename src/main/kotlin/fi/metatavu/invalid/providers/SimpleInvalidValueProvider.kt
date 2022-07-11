package fi.metatavu.invalid.providers

import fi.metatavu.invalid.InvalidValueProvider

/**
 * Simple invalid value provider
 *
 * @param value value
 */
class SimpleInvalidValueProvider(override val value: Any?): InvalidValueProvider {

}