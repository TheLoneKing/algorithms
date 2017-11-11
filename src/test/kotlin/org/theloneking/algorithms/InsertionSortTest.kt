package org.theloneking.algorithms

import org.junit.Test

class InsertionSortTest {

    @Test
    fun sortThousandRandomNumbers() {
        val count = 1000
        val testArray = IntArray(count)
        var i = 0
        while (i < testArray.size) {
            testArray[i] = (Math.random() * count).toInt()
            i++
        }
        val startTime: Long = System.currentTimeMillis()
        InsertionSort.sort(testArray)
        println("The task with 1000 numbers took " + (System.currentTimeMillis() - startTime) + " ms")
    }

    @Test
    fun sortTenThousandRandomNumbers() {
        val count = 10000
        val testArray = IntArray(count)
        var i = 0
        while (i < testArray.size) {
            testArray[i] = (Math.random() * count).toInt()
            i++
        }
        val startTime: Long = System.currentTimeMillis()
        InsertionSort.sort(testArray)
        println("The task with 10,000 numbers took " + (System.currentTimeMillis() - startTime) + " ms")
    }

    @Test
    fun sortHundredThousandRandomNumbers() {
        val count = 100000
        val testArray = IntArray(count)
        var i = 0
        while (i < testArray.size) {
            testArray[i] = (Math.random() * count).toInt()
            i++
        }
        val startTime: Long = System.currentTimeMillis()
        InsertionSort.sort(testArray)
        println("The task with 100,000 numbers took " + (System.currentTimeMillis() - startTime) + " ms")
    }

    /*@Test
    fun sortOneMillionRandomNumbers() {
        val count = 1000000
        val testArray = IntArray(count)
        var i = 0
        while (i < testArray.size) {
            testArray[i] = (Math.random() * count).toInt()
            i++
        }
        val startTime: Long = System.currentTimeMillis()
        InsertionSort.sort(testArray)
        println("The task with 1,000,000 numbers took " + (System.currentTimeMillis() - startTime) + " ms")
    }*/
}