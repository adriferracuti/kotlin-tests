package org.adriferracuti.concurrency

import org.junit.Test

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