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

    fun rank(element: Int): Int {
        return calculateRank(element, 0)
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
                tree[currentIndex].subTreeSize++
                true
            } else {
                val inserted = findPositionAndInsert(element, tree[currentIndex].rightPointer ?: tree.size)
                if (inserted) {
                    tree[currentIndex].subTreeSize++
                }
                inserted
            }
        } else {
            return if (tree[currentIndex].leftPointer == null) {
                val newNode = BinarySearchTreeNode(element)
                newNode.parentPointer = currentIndex
                tree.add(newNode)
                tree[currentIndex].leftPointer = tree.size - 1
                tree[currentIndex].subTreeSize++
                true
            } else {
                val inserted = findPositionAndInsert(element, tree[currentIndex].leftPointer ?: tree.size)
                if (inserted) {
                    tree[currentIndex].subTreeSize++
                }
                inserted
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

    private fun calculateRank(element: Int, index: Int): Int {
        var rank = 0
        val leftPtr = tree[index].leftPointer
        val rightPtr = tree[index].rightPointer
        if (tree[index].data <= element) {
            rank++
            rank = if (leftPtr != null) rank + tree[leftPtr].subTreeSize else rank
            if (rightPtr != null) {
                rank += calculateRank(element, rightPtr)
            }
        } else if (leftPtr != null) {
            rank += calculateRank(element, leftPtr)
        }
        return rank
    }

    inner class BinarySearchTreeNode(val data: Int) {
        var parentPointer: Int? = null
        var rightPointer: Int? = null
        var leftPointer: Int? = null
        var subTreeSize: Int = 1
    }
}