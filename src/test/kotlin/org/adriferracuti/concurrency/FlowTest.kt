package org.adriferracuti.concurrency

import org.junit.jupiter.api.Test

class FlowTest {

    @Test
    fun `Flow`() {
        runFlow();
    }

    @Test
    fun `Suspended`() {
        runSuspended();
    }
}
