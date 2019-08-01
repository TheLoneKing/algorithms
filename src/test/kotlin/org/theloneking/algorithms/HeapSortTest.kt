package org.theloneking.algorithms

import kotlin.test.Test

class HeapSortTest: BaseTest() {
    @Test
    fun sortThousandRandomNumbers() {
        val testArray = generateTestArray(1000)
        val startTime = System.currentTimeMillis()
        val sortedArray = HeapSort.sort(testArray)
        println("The task with 1,000 numbers took " + (System.currentTimeMillis() - startTime) + " ms")
    }

    @Test
    fun sortTenThousandRandomNumbers() {
        val testArray = generateTestArray(10000)
        val startTime = System.currentTimeMillis()
        val sortedArray = HeapSort.sort(testArray)
        println("The task with 10,000 numbers took " + (System.currentTimeMillis() - startTime) + " ms")
    }

    @Test
    fun sortHundredThousandRandomNumbers() {
        val testArray = generateTestArray(100000)
        val startTime = System.currentTimeMillis()
        val sortedArray = HeapSort.sort(testArray)
        println("The task with 100,000 numbers took " + (System.currentTimeMillis() - startTime) + " ms")
    }

    @Test
    fun sortOneMillionRandomNumbers() {
        val testArray = generateTestArray(1000000)
        val startTime = System.currentTimeMillis()
        val sortedArray = HeapSort.sort(testArray)
        println("The task with 1,000,000 numbers took " + (System.currentTimeMillis() - startTime) + " ms")
    }
}