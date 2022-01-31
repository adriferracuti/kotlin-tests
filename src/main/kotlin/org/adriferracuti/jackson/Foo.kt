package org.adriferracuti.jackson

import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import java.util.*

data class Foo(
    val localesUsingBuiltinDeserializer: List<Locale>,

    val locale: Locale,

    @JsonDeserialize(contentUsing = LocaleDeserializer::class)
    val localesUsingCustomDeserializer: List<Locale>
)
