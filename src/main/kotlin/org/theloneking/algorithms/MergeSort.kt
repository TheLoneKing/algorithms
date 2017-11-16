package org.theloneking.algorithms

object MergeSort {
    fun sort(givenArray: IntArray): IntArray {
        val newArray = copyArray(givenArray)
        topDownSplitMerge(givenArray, 0, givenArray.size, newArray)
        return newArray
    }

    private fun topDownSplitMerge(givenArray: IntArray, iBegin: Int, iEnd: Int, newArray: IntArray) {
        if (iEnd - iBegin < 2) {
            return
        }

        val isSubArrayCountEven = (iEnd + iBegin) % 2 == 0
        val iMiddle = if (isSubArrayCountEven) (iEnd + iBegin) / 2 else ((iEnd + iBegin) / 2) + 1

        topDownSplitMerge(newArray, iBegin, iMiddle, givenArray)
        topDownSplitMerge(newArray, iMiddle, iEnd, givenArray)
        topDownMerge(givenArray, iBegin, iMiddle, iEnd, newArray)
    }

    private fun topDownMerge(newArray: IntArray, iBegin: Int, iMiddle: Int, iEnd: Int, givenArray: IntArray) {
        var i = iBegin
        var j = iMiddle
        for (k in iBegin until iEnd) {
            if (i < iMiddle && (j >= iEnd || newArray[i] < newArray[j])) {
                givenArray[k] = newArray[i]
                i++
            } else {
                givenArray[k] = newArray[j]
                j++
            }
        }
    }

    private fun copyArray(givenArray: IntArray): IntArray {
        val newArray = IntArray(givenArray.size)
        for (i in 0 until givenArray.size) {
            newArray[i] = givenArray[i]
        }
        return newArray
    }
}