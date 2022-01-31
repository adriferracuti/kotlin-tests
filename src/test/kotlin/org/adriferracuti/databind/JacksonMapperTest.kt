package org.adriferracuti.databind

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.exc.MismatchedInputException
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import com.fasterxml.jackson.module.kotlin.readValue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

inline fun <reified T> String.jsonDecode(mapper: ObjectMapper = jacksonObjectMapper()): T =
    mapper.readValue(this, T::class.java)

inline fun <reified T> String.jsonDecode2(mapper: ObjectMapper = jacksonObjectMapper()): T =
    mapper.readValue(this, jacksonTypeRef<T>())

typealias Pairs = List<Pair<String, String>>

typealias MapOfPairs = Map<String, Pairs>

class JacksonMapperTest {

    private val json =
        "{\"family\": [[0, 191]], \"balcony\": [[192, 276]], \"location\": [[0, 191]], \"swimming pool\": [[0, 191]]}"

    @Test
    fun `test jsonDecode does not throw exceptions`() {
        // it does not throw errors, even though the real type seems to be LinkedHashMap<String<ArrayList<ArrayList<String>>>>
        @Suppress("UNUSED_VARIABLE") val myPairs1: MapOfPairs = json.jsonDecode()
    }

    @Test
    fun `test jsonDecode2 does not throw exceptions`() {
        // it throws a "Cannot deserialize instance of `kotlin.Pair` out of START_ARRAY token" error,
        // because the returned type does not match
        assertThrows<MismatchedInputException> {
            @Suppress("UNUSED_VARIABLE") val myPairs1: MapOfPairs = json.jsonDecode2()
        }

    }

    @Test
    fun `test readValue throws exception`() {
        val mapper = jacksonObjectMapper()

        // it throws a "Cannot deserialize instance of `kotlin.Pair` out of START_ARRAY token" error,
        // because the returned type does not match
        assertThrows<MismatchedInputException> {
            @Suppress("UNUSED_VARIABLE") val myPairs2: MapOfPairs = mapper.readValue(json)
        }
    }
}
