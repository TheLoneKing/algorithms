package org.theloneking.algorithms

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class BinarySearchTreeTest: BaseTest() {
    @Test
    fun testInsertAndContainsNode() {
        val testArray: Array<Int> = arrayOf(32, 43, 11, 9, 4, 55, 99, 1, 19, 10)
        val binarySearchTree = BinarySearchTree()
        testArray.forEach { binarySearchTree.insert(it) }

        assertTrue { binarySearchTree.containsNode(32) }

        assertTrue { binarySearchTree.containsNode(43) }

        assertTrue { binarySearchTree.containsNode(11) }

        assertTrue { binarySearchTree.containsNode(9) }

        assertTrue { binarySearchTree.containsNode(4) }

        assertTrue { binarySearchTree.containsNode(55) }

        assertTrue { binarySearchTree.containsNode(99) }

        assertTrue { binarySearchTree.containsNode(1) }

        assertTrue { binarySearchTree.containsNode(19) }

        assertTrue { binarySearchTree.containsNode(10) }

        assertFalse { binarySearchTree.containsNode(0) }
    }

    @Test
    fun testRankCalculation() {
        val testArray: Array<Int> = arrayOf(32, 43, 11, 9, 4, 55, 99, 1, 19, 10)
        val binarySearchTree = BinarySearchTree()
        testArray.forEach { binarySearchTree.insert(it) }

        assertEquals(6, binarySearchTree.rank(19))

        assertEquals(9, binarySearchTree.rank(55))

        assertEquals(7, binarySearchTree.rank(32))

        assertEquals(1, binarySearchTree.rank(1))

        assertEquals(0, binarySearchTree.rank(0))
    }

    @Test
    fun testRemoveNode() {
        val testArray: Array<Int> = arrayOf(32, 43, 11, 9, 4, 55, 99, 1, 19, 10, 35, 34, 37)
        val binarySearchTree = BinarySearchTree()
        testArray.forEach { binarySearchTree.insert(it) }

        binarySearchTree.remove(10)
        binarySearchTree.remove(4)
        binarySearchTree.remove(43)

        assertFalse(binarySearchTree.containsNode(10))
        assertFalse(binarySearchTree.containsNode(4))
        assertFalse(binarySearchTree.containsNode(43))
    }

    @Test
    fun testInOrderTraversal() {
        val testArray: Array<Int> = arrayOf(32, 43, 11, 9, 4, 55, 99, 1, 19, 10)
        val binarySearchTree = BinarySearchTree()
        testArray.forEach { binarySearchTree.insert(it) }

        val expectedString = "1, 4, 9, 10, 11, 19, 32, 43, 55, 99"

        assertEquals(expectedString, binarySearchTree.traverseInOrder())

        val emptyTree = BinarySearchTree()

        assertEquals("", emptyTree.traverseInOrder())
    }

    @Test
    fun testPreOrderTraversal() {
        val testArray: Array<Int> = arrayOf(32, 43, 11, 9, 4, 55, 99, 1, 19, 10)
        val binarySearchTree = BinarySearchTree()
        testArray.forEach { binarySearchTree.insert(it) }

        val expectedString = "32, 11, 9, 4, 1, 10, 19, 43, 55, 99"

        assertEquals(expectedString, binarySearchTree.traversePreOrder())

        val emptyTree = BinarySearchTree()

        assertEquals("", emptyTree.traversePreOrder())
    }

    @Test
    fun testPostOrderTraversal() {
        val testArray: Array<Int> = arrayOf(32, 43, 11, 9, 4, 55, 99, 1, 19, 10)
        val binarySearchTree = BinarySearchTree()
        testArray.forEach { binarySearchTree.insert(it) }

        val expectedString = "1, 4, 10, 9, 19, 11, 99, 55, 43, 32"

        assertEquals(expectedString, binarySearchTree.traversePostOrder())

        val emptyTree = BinarySearchTree()

        assertEquals("", emptyTree.traversePostOrder())
    }

    @Test
    fun testLevelOrderTraversal() {
        val testArray: Array<Int> = arrayOf(32, 43, 11, 9, 4, 55, 99, 1, 19, 10)
        val binarySearchTree = BinarySearchTree()
        testArray.forEach { binarySearchTree.insert(it) }

        val expectedString = "32, 11, 43, 9, 19, 55, 4, 10, 99, 1"

        assertEquals(expectedString, binarySearchTree.traverseLevelOrder())

        val emptyTree = BinarySearchTree()

        assertEquals("", emptyTree.traverseLevelOrder())
    }
}