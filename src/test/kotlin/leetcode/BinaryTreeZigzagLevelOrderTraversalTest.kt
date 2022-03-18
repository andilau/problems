package leetcode

// 103. Binary Tree Zigzag Level Order Traversal
// https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/

import org.junit.jupiter.api.Test
import kotlin.test.assertContentEquals

class BinaryTreeZigzagLevelOrderTraversalTest {

    class Node(var value: Int) {
        var left: Node? = null
        var right: Node? = null
    }

    val a = Node(1)
    val b = Node(2)
    val c = Node(3)
    val d = Node(4)
    val e = Node(5)

    private fun zigzagLevelOrder(node: Node): List<List<Int>> {
        val result: MutableList<MutableList<Int>> = arrayListOf()
        traverse(node, result, 0)
        return result
    }

    private fun traverse(node: Node?, result: MutableList<MutableList<Int>>, level: Int): List<List<Int>> {
        if (node == null) return emptyList()

        while (result.size <= level)
            result += mutableListOf<Int>()

        val list = result[level]
        if (level % 2 == 0) list.add(node.value)
        else list.add(0, node.value)

        traverse(node.left, result, level + 1)
        traverse(node.right, result, level + 1)

        return result
    }

    @Test
    fun testLevel1() {
        assertContentEquals(
            listOf(listOf(1)),
            zigzagLevelOrder(a)
        )
    }

    @Test
    fun testLevel2() {
        a.left = b
        a.right = c

        assertContentEquals(
            listOf(listOf(1), listOf(3, 2)),
            zigzagLevelOrder(a)
        )
    }

    @Test
    fun testLevel3() {
        a.left = b
        a.right = c
        b.left = d
        b.right = e

        assertContentEquals(
            listOf(listOf(1), listOf(3, 2), listOf(4, 5)),
            zigzagLevelOrder(a)
        )
    }
}