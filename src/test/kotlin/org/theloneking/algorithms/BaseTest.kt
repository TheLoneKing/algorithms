package org.theloneking.algorithms

open class BaseTest {
    fun generateTestArray(count: Int): IntArray {
        val testArray = IntArray(count)
        for (i in 0 until count) {
            testArray[i] = (Math.random() * count).toInt()
        }
        return testArray
    }
}