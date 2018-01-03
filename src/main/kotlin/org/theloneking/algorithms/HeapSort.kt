package org.theloneking.algorithms

/**
 * Heap Sort Algorithm:
 * 1. build_max_heap from the unordered array
 * 2. find max element A[first]
 * 3. swap elements A[last] and A[first]. Now the max element will be at the end of the array
 * 4. discard last node by decrementing heap_size
 * 5. new root may violate max_heap_property. But the children are max_heaps. Run max_heapify
 * 6. go to step 2
 */

object HeapSort {
    fun sort(givenArray: IntArray): IntArray {
        var heapSize = givenArray.size
        buildMaxHeap(givenArray, heapSize)
        while (heapSize > 1) {
            heapSize = swapMaxElement(givenArray, heapSize)
            maxHeapify(givenArray, 0, heapSize)
        }
        return givenArray
    }

    private fun buildMaxHeap(givenArray: IntArray, heapSize: Int) {
        for (position in heapSize / 2 downTo 1) {
            val index = position - 1
            val leftChildIndex = 2 * index + 1
            val rightChildIndex = 2 * index + 2

            if (givenArray[index] < givenArray[leftChildIndex]) {
                var temp = givenArray[index]
                givenArray[index] = givenArray[leftChildIndex]
                givenArray[leftChildIndex] = temp
            }

            if (rightChildIndex < heapSize && givenArray[index] < givenArray[rightChildIndex]) {
                var temp = givenArray[index]
                givenArray[index] = givenArray[rightChildIndex]
                givenArray[rightChildIndex] = temp
            }
        }
    }

    private fun swapMaxElement(givenArray: IntArray, heapSize: Int): Int {
        val lastIndex = heapSize - 1
        var temp = givenArray[0]
        givenArray[0] = givenArray[lastIndex]
        givenArray[lastIndex] = temp
        return lastIndex
    }

    private fun maxHeapify(givenArray: IntArray, index: Int, heapSize: Int) {
        var leftSwap = false
        var rightSwap = false
        val leftChildIndex = 2 * index + 1
        val rightChildIndex = 2 * index + 2

        if (leftChildIndex < heapSize && givenArray[index] < givenArray[leftChildIndex]) {
            var temp = givenArray[index]
            givenArray[index] = givenArray[leftChildIndex]
            givenArray[leftChildIndex] = temp
            leftSwap = true
        }

        if (rightChildIndex < heapSize && givenArray[index] < givenArray[rightChildIndex]) {
            var temp = givenArray[index]
            givenArray[index] = givenArray[rightChildIndex]
            givenArray[rightChildIndex] = temp
            rightSwap = true
        }

        if (leftSwap) {
            maxHeapify(givenArray, leftChildIndex, heapSize)
        }

        if (rightSwap) {
            maxHeapify(givenArray, rightChildIndex, heapSize)
        }
    }
}