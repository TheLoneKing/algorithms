package org.theloneking.algorithms

object HeapSort {
    fun sort(givenArray: IntArray): IntArray {
        var heapSize = givenArray.size
        buildMaxHeap(givenArray)
        while (heapSize > 1) {
            heapSize = swapMaxElement(givenArray, heapSize)
            maxHeapify(givenArray, heapSize)
        }
        return givenArray
    }

    private fun buildMaxHeap(givenArray: IntArray) {
        // TODO: Implement build_max_heap function

    }

    private fun swapMaxElement(givenArray: IntArray, heapSize: Int): Int {
        var temp = givenArray[0]
        givenArray[0] = givenArray[heapSize - 1]
        givenArray[heapSize - 1] = temp
        return heapSize - 1
    }

    private fun maxHeapify(givenArray: IntArray, heapSize: Int) {
        // TODO: Implement max_heapify function

    }
}