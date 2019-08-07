package org.theloneking.algorithms

class BinarySearchTree {
    private var rootNode: BinarySearchTreeNode? = null

    fun insert(element: Int) {
        rootNode = insertRecursive(element, rootNode)
    }

    fun containsNode(element: Int): Boolean {
        return containsNodeRecursive(element, rootNode)
    }

    /**
     * Rank is the number of nodes in the tree whose values are either less than or equal to a given value.
     * This method will return the rank for a given value.
     */
    fun rank(element: Int): Int {
        return calculateRank(element, rootNode)
    }

    // TODO
    fun remove(element: Int): Boolean {
        return false
    }

    private fun insertRecursive(element: Int, currentNode: BinarySearchTreeNode?): BinarySearchTreeNode {
        if (currentNode == null) {
            return BinarySearchTreeNode(element)
        }

        when {
            element < currentNode.value -> currentNode.leftNode = insertRecursive(element, currentNode.leftNode)
            element > currentNode.value -> currentNode.rightNode = insertRecursive(element, currentNode.rightNode)
            else -> return currentNode // element == currentNode.value (element is already present in the tree)
        }

        // Recalculate subtree sizes
        currentNode.subTreeSize = 1 + (currentNode.leftNode?.subTreeSize ?: 0) + (currentNode.rightNode?.subTreeSize ?: 0)
        return currentNode
    }

    private fun containsNodeRecursive(element: Int, currentNode: BinarySearchTreeNode?): Boolean {
        return if (currentNode != null) {
            when {
                element < currentNode.value -> containsNodeRecursive(element, currentNode.leftNode)
                element > currentNode.value -> containsNodeRecursive(element, currentNode.rightNode)
                else -> true // element == currentNode.value
            }
        } else {
            false
        }
    }

    private fun calculateRank(element: Int, currentNode: BinarySearchTreeNode?): Int {
        var rank = 0

        if (currentNode != null) {
            if (currentNode.value <= element) {
                rank++ // for the current node
                rank += currentNode.leftNode?.subTreeSize ?: 0 // all nodes in left subtree are < element
                rank += calculateRank(element, currentNode.rightNode) // traverse right subtree & see if any node < element
            } else {
                rank += calculateRank(element, currentNode.leftNode) // traverse through left subtree & see if any node < element
            }
        }

        return rank
    }

    inner class BinarySearchTreeNode(val value: Int) {
        var rightNode: BinarySearchTreeNode? = null
        var leftNode: BinarySearchTreeNode? = null
        var subTreeSize: Int = 1
    }
}