package org.adriferracuti.collection

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class MapTest {

    @Test
    fun `size, count`() {
        val map = mapOf(
            "bu" to listOf(1, 2, 3),
            "foo" to 3,
            "bar" to 5
        )

        assertEquals(3, map.size)
        assertEquals(3, map.count())
    }
}
