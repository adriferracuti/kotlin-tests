package org.adriferracuti.concurrency

import java.lang.Thread.sleep
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

fun simple(): Sequence<Int> = sequence { // sequence builder
    for (i in 1..3) {
        sleep(100) // pretend we are computing it
        yield(i) // yield next value
    }
}

suspend fun simpleSuspended(): List<Int> {
    delay(1000) // pretend we are doing something asynchronous here
    return listOf(1, 2, 3)
}

fun simpleFlow(): Flow<Int> = flow { // flow builder
    for (i in 1..3) {
        delay(100) // pretend we are doing something useful here
        emit(i) // emit next value
    }
}

fun runFlow() {
    runBlocking {
        // Launch a concurrent coroutine to check if the main thread is blocked
        launch {
            for (k in 1..3) {
                println("I'm not blocked $k")
                delay(100)
            }
        }
        // Collect the flow
        simpleFlow().collect { value -> println(value) }
    }
}

fun runSuspended() {
    runBlocking {
        simpleSuspended().forEach { value -> println(value) }
    }
}