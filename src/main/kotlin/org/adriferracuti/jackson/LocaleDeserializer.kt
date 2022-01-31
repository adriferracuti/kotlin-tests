package org.adriferracuti.jackson

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import org.springframework.boot.jackson.JsonComponent
import org.springframework.core.convert.converter.Converter
import java.util.*

// Autowires it.
// It means that every property expecting Locale type will be deserialized using this class, no matter of the JsonDeserialize annotation.
@JsonComponent
class LocaleDeserializer() : JsonDeserializer<Locale>(), Converter<String, Locale> {
    // Germanizer
    override fun deserialize(jsonParser: JsonParser, ctxt: DeserializationContext?): Locale {
        return toLocale("de-DE")
    }

    override fun convert(source: String): Locale = toLocale(source)

    private fun toLocale(rawLocale: String): Locale {
        return Locale.forLanguageTag(rawLocale)
    }
}
