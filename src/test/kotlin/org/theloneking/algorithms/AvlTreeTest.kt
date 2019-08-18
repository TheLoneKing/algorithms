package org.theloneking.algorithms

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class AvlTreeTest {
    @Test
    fun testInsertAndContainsNode() {
        val testArray: Array<Int> = arrayOf(32, 43, 11, 9, 4, 55, 99, 1, 19, 10)
        val avlTree = AvlTree()
        testArray.forEach { avlTree.insert(it) }

        assertTrue { avlTree.containsNode(32) }

        assertTrue { avlTree.containsNode(43) }

        assertTrue { avlTree.containsNode(11) }

        assertTrue { avlTree.containsNode(9) }

        assertTrue { avlTree.containsNode(4) }

        assertTrue { avlTree.containsNode(55) }

        assertTrue { avlTree.containsNode(99) }

        assertTrue { avlTree.containsNode(1) }

        assertTrue { avlTree.containsNode(19) }

        assertTrue { avlTree.containsNode(10) }

        assertFalse { avlTree.containsNode(0) }
    }

    @Test
    fun testInOrderTraversal() {
        val testArray: Array<Int> = arrayOf(32, 43, 11, 9, 4, 55, 99, 1, 19, 10)
        val avlTree = AvlTree()
        testArray.forEach { avlTree.insert(it) }

        val expectedString = "1, 4, 9, 10, 11, 19, 32, 43, 55, 99"

        assertEquals(expectedString, avlTree.traverseInOrder())
    }

    @Test
    fun testRemoveNode() {
        val testArray: Array<Int> = arrayOf(32, 43, 11, 9, 4, 55, 99, 1, 19, 10)
        val avlTree = AvlTree()
        testArray.forEach { avlTree.insert(it) }

        avlTree.remove(10)
        avlTree.remove(4)
        avlTree.remove(43)

        assertFalse { avlTree.containsNode(10) }
        assertFalse { avlTree.containsNode(4) }
        assertFalse { avlTree.containsNode(43) }
        assertTrue { avlTree.containsNode(11) }
    }
}