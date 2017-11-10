package org.theloneking.algorithms

object InsertionSort {
    fun sort(givenArray: IntArray): IntArray {
        var i: Int = 1
        while (i < givenArray.size) {
            var j: Int = i - 1
            val currentValue: Int = givenArray[i]
            while (j >= 0 && givenArray[j] > currentValue) {
                givenArray[j + 1] = givenArray[j]
                j--
            }
            givenArray[j + 1] = currentValue
            i++
        }
        return givenArray
    }
}