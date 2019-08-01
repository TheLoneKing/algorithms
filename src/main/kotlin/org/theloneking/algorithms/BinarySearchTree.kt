package org.theloneking.algorithms

class BinarySearchTree {
    val tree: MutableList<BinarySearchTreeNode> = mutableListOf()

    fun insert(element: Int): Boolean {
        return when (tree.size) {
            0 -> tree.add(BinarySearchTreeNode(element))
            else -> findPositionAndInsert(element, 0)
        }
    }

    fun find(element: Int): Int {
        return when {
            (tree.size > 0) -> findPosition(element, 0)
            else -> -1
        }
    }

    // TODO
    fun rank(element: Int): Int {
        return 0
    }

    // TODO
    fun remove(element: Int): Boolean {
        return false
    }

    private fun findPositionAndInsert(element: Int, currentIndex: Int): Boolean {
        if (element >= tree[currentIndex].data) {
            return if (tree[currentIndex].rightPointer == null) {
                val newNode = BinarySearchTreeNode(element)
                newNode.parentPointer = currentIndex
                tree.add(newNode)
                tree[currentIndex].rightPointer = tree.size - 1
                true
            } else {
                findPositionAndInsert(element, tree[currentIndex].rightPointer ?: tree.size)
            }
        } else {
            return if (tree[currentIndex].leftPointer == null) {
                val newNode = BinarySearchTreeNode(element)
                newNode.parentPointer = currentIndex
                tree.add(newNode)
                tree[currentIndex].leftPointer = tree.size - 1
                true
            } else {
                findPositionAndInsert(element, tree[currentIndex].leftPointer ?: tree.size)
            }
        }
    }

    private fun findPosition(element: Int, currentIndex: Int?): Int {
        return when {
            (currentIndex != null && element == tree[currentIndex].data) -> currentIndex
            (currentIndex != null && element > tree[currentIndex].data) -> findPosition(element, tree[currentIndex].rightPointer)
            (currentIndex != null && element < tree[currentIndex].data) -> findPosition(element, tree[currentIndex].leftPointer)
            else -> -1
        }
    }

    inner class BinarySearchTreeNode(val data: Int) {
        var parentPointer: Int? = null
        var rightPointer: Int? = null
        var leftPointer: Int? = null
    }
}