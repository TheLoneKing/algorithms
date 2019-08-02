package org.theloneking.algorithms

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class BinarySearchTreeTest: BaseTest() {
    @Test
    fun testInsertAndFind() {
        val testArray: Array<Int> = arrayOf(32, 43, 11, 9, 4, 55, 99, 1, 19, 10)
        val binarySearchTree = BinarySearchTree()
        testArray.forEach { binarySearchTree.insert(it) }

        val indexOfTen = binarySearchTree.find(10)
        assertNull(binarySearchTree.tree[indexOfTen].leftPointer)
        assertNull(binarySearchTree.tree[indexOfTen].rightPointer)
        assertEquals(3, binarySearchTree.tree[indexOfTen].parentPointer)

        val indexOfNineteen = binarySearchTree.find(19)
        assertNull(binarySearchTree.tree[indexOfNineteen].leftPointer)
        assertNull(binarySearchTree.tree[indexOfNineteen].rightPointer)
        assertEquals(2, binarySearchTree.tree[indexOfNineteen].parentPointer)

        val indexOfNine = binarySearchTree.find(9)
        assertEquals(4, binarySearchTree.tree[indexOfNine].leftPointer)
        assertEquals(9, binarySearchTree.tree[indexOfNine].rightPointer)
        assertEquals(2, binarySearchTree.tree[indexOfNine].parentPointer)
    }

    @Test
    fun testSubTreeSize() {
        val testArray: Array<Int> = arrayOf(32, 43, 11, 9, 4, 55, 99, 1, 19, 10)
        val binarySearchTree = BinarySearchTree()
        testArray.forEach { binarySearchTree.insert(it) }

        val indexOfTen = binarySearchTree.find(10)
        assertEquals(1, binarySearchTree.tree[indexOfTen].subTreeSize)

        val indexOfEleven = binarySearchTree.find(11)
        assertEquals(6, binarySearchTree.tree[indexOfEleven].subTreeSize)

        val indexOfFortyThree = binarySearchTree.find(43)
        assertEquals(3, binarySearchTree.tree[indexOfFortyThree].subTreeSize)

        val indexOfThirtyTwo = binarySearchTree.find(32)
        assertEquals(10, binarySearchTree.tree[indexOfThirtyTwo].subTreeSize)
    }

    @Test
    fun testRankCalculation() {
        val testArray: Array<Int> = arrayOf(32, 43, 11, 9, 4, 55, 99, 1, 19, 10)
        val binarySearchTree = BinarySearchTree()
        testArray.forEach { binarySearchTree.insert(it) }

        val rankOfNineteen = binarySearchTree.rank(19)
        assertEquals(6, rankOfNineteen)

        val rankOfFiftyFive = binarySearchTree.rank(55)
        assertEquals(9, rankOfFiftyFive)

        val rankOfThirtyTwo = binarySearchTree.rank(32)
        assertEquals(7, rankOfThirtyTwo)

        val rankOfOne = binarySearchTree.rank(1)
        assertEquals(1, rankOfOne)
    }
}