package org.adriferracuti.memory

// non-leaking because data class implements equals and hashCode properly
private data class MyNonLeakingKey(val id: Int)

fun main() {
    val map = mutableMapOf<MyNonLeakingKey, String>()

    var i = 0
    while (true) {
        map[MyNonLeakingKey(5)] = "foo"
        i++
        if (i % 1000 == 0) {
            println(map.size)
            Thread.sleep(10)
        }
    }
}
