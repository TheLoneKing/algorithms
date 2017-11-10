package org.theloneking.algorithms

import org.junit.Test

class InsertionSortTest {

    @Test
    fun sort() {
        val testArray: IntArray = intArrayOf(5, 4, 3, 2, 1)
        InsertionSort.sort(testArray).forEach { i -> println(i) }
    }
}