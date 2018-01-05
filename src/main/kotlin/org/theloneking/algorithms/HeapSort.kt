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
        var lastHeapNodeIndex = givenArray.size - 1
        buildMaxHeap(givenArray, lastHeapNodeIndex)
        while (lastHeapNodeIndex > 0) {
            swapHeapNodes(givenArray, 0, lastHeapNodeIndex)
            lastHeapNodeIndex--
            maxHeapify(givenArray, 0, lastHeapNodeIndex)
        }
        return givenArray
    }

    private fun buildMaxHeap(givenArray: IntArray, lastHeapNodeIndex: Int) {
        var rootIndex = parentIndex(lastHeapNodeIndex)
        while (rootIndex >= 0) {
            maxHeapify(givenArray, rootIndex, lastHeapNodeIndex)
            rootIndex--
        }
    }

    private fun swapHeapNodes(givenArray: IntArray, indexOne: Int, indexTwo: Int) {
        var temp = givenArray[indexOne]
        givenArray[indexOne] = givenArray[indexTwo]
        givenArray[indexTwo] = temp
    }

    private fun maxHeapify(givenArray: IntArray, index: Int, lastHeapNodeIndex: Int) {
        var root = index
        while (leftChildIndex(root) <= lastHeapNodeIndex) {
            var leftChildIndex = leftChildIndex(root)
            var swap = root
            if (givenArray[swap] < givenArray[leftChildIndex]) {
                swap = leftChildIndex
            }

            if (leftChildIndex + 1 <= lastHeapNodeIndex && givenArray[swap] < givenArray[leftChildIndex + 1]) {
                swap = leftChildIndex + 1
            }

            if (swap != root) {
                swapHeapNodes(givenArray, root, swap)
                root = swap
            } else {
                return
            }
        }
    }

    private fun leftChildIndex(parentIndex: Int): Int {
        return 2 * parentIndex + 1
    }

    private fun parentIndex(childIndex: Int): Int {
        return (childIndex - 1 + (childIndex % 2)) / 2
    }
}