package org.adriferracuti.databind

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.kotlinModule
import org.adriferracuti.jackson.Foo
import org.intellij.lang.annotations.Language
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.util.*

@Language("JSON")
private const val JSON = """{
  "locale": "es-ES",
  "localesUsingBuiltinDeserializer": [
    "it-IT"
  ],
  "localesUsingCustomDeserializer": [
    "en-GB"
  ]
}"""

@SpringBootTest // LocaleDeserializer is autowired to Jackson due to @JsonComponent
class JacksonDeserializerTest(
    @Autowired val mapperAutowired: ObjectMapper
) {
    private val mapperNotAutowired: ObjectMapper = ObjectMapper()
        // for supporting data class constructors
        .registerModule(kotlinModule())

    @Test
    fun `Given deserializer not autowired via @JsonComponent, when deserialized, then only field annotated with @JsonDeserialize is remapped to de-DE`() {
        val foo: Foo = mapperNotAutowired.readValue(JSON, Foo::class.java)

        assertEquals(Locale.forLanguageTag("es-ES"), foo.locale)

        /** @see com.fasterxml.jackson.databind.deser.std.FromStringDeserializer.Std */
        assertEquals(listOf(Locale.forLanguageTag("it-IT")), foo.localesUsingBuiltinDeserializer)

        /** @see org.adriferracuti.jackson.LocaleDeserializer */
        assertEquals(listOf(Locale.forLanguageTag("de-DE")), foo.localesUsingCustomDeserializer)
    }

    @Test
    fun `Given deserializer autowired via @JsonComponent, when deserialized, then all locales are remapped to de-DE`() {
        val foo: Foo = mapperAutowired.readValue(JSON, Foo::class.java)

        assertEquals(Locale.forLanguageTag("de-DE"), foo.locale)
        assertEquals(listOf(Locale.forLanguageTag("de-DE")), foo.localesUsingBuiltinDeserializer)
        assertEquals(listOf(Locale.forLanguageTag("de-DE")), foo.localesUsingCustomDeserializer)
    }

    @Test
    fun `Given deserializer autowired via @JsonComponent and simple field is missing, when deserialized, then a default value en-CA is provided`() {
        val JSON = """{
  "localesUsingBuiltinDeserializer": [
    "it-IT"
  ],
  "localesUsingCustomDeserializer": [
    "en-GB"
  ]
}"""
        val foo: Foo = mapperAutowired.readValue(JSON, Foo::class.java)
        assertEquals(Locale.CANADA, foo.locale)
        assertEquals(listOf(Locale.forLanguageTag("de-DE")), foo.localesUsingBuiltinDeserializer)
        assertEquals(listOf(Locale.forLanguageTag("de-DE")), foo.localesUsingCustomDeserializer)
    }
}
