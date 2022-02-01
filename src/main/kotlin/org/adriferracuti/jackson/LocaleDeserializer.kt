package org.adriferracuti.jackson

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import org.springframework.boot.jackson.JsonComponent
import java.util.*

// Autowires it.
// It means that every property expecting Locale type will be deserialized using this class, no matter of the JsonDeserialize annotation.
@JsonComponent
class LocaleDeserializer() : JsonDeserializer<Locale>() {
    override fun deserialize(jsonParser: JsonParser, ctxt: DeserializationContext?): Locale {
        return Locale.GERMANY
    }

    // Gets called in case of absent value
    // but only if the property is instantiated via @JsonCreator
    override fun getNullValue(ctxt: DeserializationContext?): Locale {
        return Locale.CANADA
    }
}
