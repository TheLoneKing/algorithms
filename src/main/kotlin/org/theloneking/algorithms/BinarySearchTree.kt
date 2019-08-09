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

    fun remove(element: Int) {
        rootNode = removeRecursive(element, rootNode)
    }

    /**
     * In-order traversal is a depth-first search.
     * This method returns an empty string if tree does not have any nodes.
     */
    fun traverseInOrder(): String {
        return recursiveInOrderTraversal(rootNode).removeSuffix(", ")
    }

    /**
     * Pre-order traversal is a depth-first search.
     * This method returns an empty string if tree does not have any nodes.
     */
    fun traversePreOrder(): String {
        return recursivePreOrderTraversal(rootNode).removeSuffix(", ")
    }

    /**
     * Post-order traversal is a depth-first search.
     * This method returns an empty string if tree does not have any nodes.
     */
    fun traversePostOrder(): String {
        return recursivePostOrderTraversal(rootNode).removeSuffix(", ")
    }

    /**
     * Level-order traversal is a breadth-first search.
     * This method returns an empty string if tree does not have any nodes.
     */
    fun traverseLevelOrder(): String {
        return if (rootNode != null) {
            val nodes = mutableListOf(rootNode!!)
            recursiveLevelOrderTraversal(nodes).removeSuffix(", ")
        } else {
            ""
        }
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

    private fun removeRecursive(element: Int, currentNode: BinarySearchTreeNode?): BinarySearchTreeNode? {
        if (currentNode == null) {
            return null
        }

        if (element == currentNode.value) {
            if (currentNode.leftNode == null && currentNode.rightNode == null) { // leaf node - no child
                return null
            } else if (currentNode.leftNode != null && currentNode.rightNode != null) { // a node with two children
                val smallestValue = findSmallestValue(currentNode.rightNode!!)
                currentNode.value = smallestValue
                currentNode.rightNode = removeRecursive(element, currentNode.rightNode)
                return currentNode
            } else { // node with one child
                return currentNode.leftNode ?: currentNode.rightNode
            }
        } else if (element < currentNode.value) {
            currentNode.leftNode = removeRecursive(element, currentNode.leftNode)
            return currentNode
        } else { // element > currentNode.value
            currentNode.rightNode = removeRecursive(element, currentNode.rightNode)
            return currentNode
        }
    }

    private fun findSmallestValue(currentNode: BinarySearchTreeNode): Int {
        return when {
            currentNode.leftNode == null -> currentNode.value
            else -> findSmallestValue(currentNode.leftNode!!)
        }
    }

    private fun recursiveInOrderTraversal(currentNode: BinarySearchTreeNode?): String {
        var treeString = ""

        if (currentNode != null) {
            treeString = recursiveInOrderTraversal(currentNode.leftNode)
            treeString += "${currentNode.value}, "
            treeString += recursiveInOrderTraversal(currentNode.rightNode)
        }

        return treeString
    }

    private fun recursivePreOrderTraversal(currentNode: BinarySearchTreeNode?): String {
        var treeString = ""

        if (currentNode != null) {
            treeString = "${currentNode.value}, "
            treeString += recursivePreOrderTraversal(currentNode.leftNode)
            treeString += recursivePreOrderTraversal(currentNode.rightNode)
        }

        return treeString
    }

    private fun recursivePostOrderTraversal(currentNode: BinarySearchTreeNode?): String {
        var treeString = ""

        if (currentNode != null) {
            treeString = recursivePostOrderTraversal(currentNode.leftNode)
            treeString += recursivePostOrderTraversal(currentNode.rightNode)
            treeString += "${currentNode.value}, "
        }

        return treeString
    }

    private fun recursiveLevelOrderTraversal(nodes: MutableList<BinarySearchTreeNode>): String {
        var treeString = ""

        if (nodes.isNotEmpty()) {
            val node = nodes.removeAt(0)
            treeString = "${node.value}, "

            if (node.leftNode != null) {
                nodes.add(node.leftNode!!)
            }

            if (node.rightNode != null) {
                nodes.add(node.rightNode!!)
            }

            treeString += recursiveLevelOrderTraversal(nodes)
        }

        return treeString
    }

    inner class BinarySearchTreeNode(var value: Int) {
        var rightNode: BinarySearchTreeNode? = null
        var leftNode: BinarySearchTreeNode? = null
        var subTreeSize: Int = 1
    }
}