package org.adriferracuti.memory

// leaking because equals and hashCode aren't implemented properly
private class MyLeakingKey(val id: Int)

fun main() {
    val map = mutableMapOf<MyLeakingKey, String>()

    var i = 0
    while (true) {
        map[MyLeakingKey(5)] = "foo"
        i++
        if (i % 1000 == 0) {
            println(map.size)
            Thread.sleep(10)
        }
    }
}
