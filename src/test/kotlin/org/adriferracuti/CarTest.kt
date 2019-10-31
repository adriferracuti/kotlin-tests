package org.adriferracuti

import org.junit.Test
import org.junit.jupiter.api.Assertions

class CarTest {
    @Test
    fun `what you get is what you set`() {
        val car = Car(12, 15)
        Assertions.assertEquals(12, car.seats)
        Assertions.assertEquals(15, car.doors)
    }
}