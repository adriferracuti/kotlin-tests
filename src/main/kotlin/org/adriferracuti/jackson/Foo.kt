package org.adriferracuti.jackson

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import java.util.*

data class Foo(
    val locale: Locale,
    val localesUsingBuiltinDeserializer: List<Locale>,


    @JsonDeserialize(contentUsing = LocaleDeserializer::class) val localesUsingCustomDeserializer: List<Locale>?
) {
    companion object {
        /**
         * Workaround to force Jackson getting the default locale from
         * @see org.adriferracuti.jackson.LocaleDeserializer.getNullValue
         * in case of missing value
         */
        @Suppress("unused")
        @JsonCreator
        fun create(@JsonProperty("locale") locale: Locale): Locale {
            return locale
        }
    }
}
