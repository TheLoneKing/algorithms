package org.theloneking.algorithms

class AvlTree {
    private var rootNode: AvlTreeNode? = null

    fun insert(element: Int) {
        rootNode = insertRecursive(element, rootNode)
    }

    fun containsNode(element: Int): Boolean {
        return containsNodeRecursive(element, rootNode)
    }

    fun remove(element: Int) {
        rootNode = removeRecursive(element, rootNode)
    }

    fun traverseInOrder(): String {
        return recursiveInOrderTraversal(rootNode).removeSuffix(", ")
    }

    private fun insertRecursive(element: Int, currentNode: AvlTreeNode?): AvlTreeNode {
        if (currentNode == null) {
            return AvlTreeNode(element)
        }

        when {
            element < currentNode.key -> currentNode.leftNode = insertRecursive(element, currentNode.leftNode)
            element > currentNode.key -> currentNode.rightNode = insertRecursive(element, currentNode.rightNode)
            else -> return currentNode // element == currentNode.key i.e., element already exists in the tree
        }

        currentNode.longestPathToLeaf = calculateLongestPathToLeaf(currentNode)

        return checkAndRotate(element, currentNode)
    }

    private fun containsNodeRecursive(element: Int, currentNode: AvlTreeNode?): Boolean {
        return if (currentNode != null) {
            when {
                element < currentNode.key -> containsNodeRecursive(element, currentNode.leftNode)
                element > currentNode.key -> containsNodeRecursive(element, currentNode.rightNode)
                else -> true // element == currentNode.key
            }
        } else {
            false
        }
    }

    private fun removeRecursive(element: Int, currentNode: AvlTreeNode?): AvlTreeNode? {
        if (currentNode == null) {
            return null
        }

        if (element == currentNode.key) {
            if (currentNode.leftNode == null && currentNode.rightNode == null) {
                // leaf node - no child
                return null
            } else if (currentNode.leftNode != null && currentNode.rightNode != null) {
                // a node with two children
                val smallestValue = findSmallestValue(currentNode.rightNode!!)
                currentNode.key = smallestValue
                currentNode.rightNode = removeRecursive(smallestValue, currentNode.rightNode)
                return currentNode
            } else {
                // node with one child
                return currentNode.leftNode ?: currentNode.rightNode
            }
        } else if (element < currentNode.key) {
            currentNode.leftNode = removeRecursive(element, currentNode.leftNode)
            return currentNode
        } else { // element > currentNode.key
            currentNode.rightNode = removeRecursive(element, currentNode.rightNode)
            return currentNode
        }
    }

    private fun recursiveInOrderTraversal(currentNode: AvlTreeNode?): String {
        var treeString = ""

        if (currentNode != null) {
            treeString = recursiveInOrderTraversal(currentNode.leftNode)
            treeString += "${currentNode.key}, "
            treeString += recursiveInOrderTraversal(currentNode.rightNode)
        }

        return treeString
    }

    private fun checkAndRotate(element: Int, currentNode: AvlTreeNode): AvlTreeNode {
        val balanceFactor = (currentNode.rightNode?.longestPathToLeaf ?: 0) - (currentNode.rightNode?.longestPathToLeaf ?: 0)
        if (balanceFactor < -1) { // tree is left heavy - so leftNode won't be null
            return if (element < currentNode.leftNode!!.key) {
                // Left-Left case
                rightRotate(currentNode)
            } else {
                // Left-Right case
                currentNode.leftNode = leftRotate(currentNode.leftNode!!)
                rightRotate(currentNode)
            }
        } else if (balanceFactor > 1) { // tree is right heavy - so rightNode won't be null
            return if (element < currentNode.rightNode!!.key) {
                // Right-Left case
                currentNode.rightNode = rightRotate(currentNode.rightNode!!)
                leftRotate(currentNode)
            } else {
                // Right-Right case
                leftRotate(currentNode)
            }
        } else {
            // tree is already balanced - no rotations necessary
            return currentNode
        }
    }

    private fun leftRotate(currentNode: AvlTreeNode): AvlTreeNode {
        val pivotNode = currentNode.rightNode!!
        val y = pivotNode.leftNode

        // Rotation
        pivotNode.leftNode = currentNode
        currentNode.rightNode = y

        // Re-calculate longest path to leaf
        currentNode.longestPathToLeaf = calculateLongestPathToLeaf(currentNode)
        pivotNode.longestPathToLeaf = calculateLongestPathToLeaf(pivotNode)

        return pivotNode
    }

    private fun rightRotate(currentNode: AvlTreeNode): AvlTreeNode {
        val pivotNode = currentNode.leftNode!!
        val y = pivotNode.rightNode

        // Rotation
        pivotNode.rightNode = currentNode
        currentNode.leftNode = y

        // Re-calculate longest path to leaf
        currentNode.longestPathToLeaf = calculateLongestPathToLeaf(currentNode)
        pivotNode.longestPathToLeaf = calculateLongestPathToLeaf(pivotNode)

        return pivotNode
    }

    private fun calculateLongestPathToLeaf(currentNode: AvlTreeNode): Int {
        return 1 + maxOf((currentNode.leftNode?.longestPathToLeaf ?: 0), (currentNode.rightNode?.longestPathToLeaf ?: 0))
    }

    private fun findSmallestValue(currentNode: AvlTreeNode): Int {
        return when {
            currentNode.leftNode == null -> currentNode.key
            else -> findSmallestValue(currentNode.leftNode!!)
        }
    }

    inner class AvlTreeNode(var key: Int) {
        var leftNode: AvlTreeNode? = null
        var rightNode: AvlTreeNode? = null
        var longestPathToLeaf: Int = 0
    }
}