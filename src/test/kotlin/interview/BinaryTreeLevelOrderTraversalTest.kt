package interview

import org.junit.jupiter.api.Test
import kotlin.test.assertContentEquals

// 102. Binary Tree Level Order Traversal
// https://leetcode.com/problems/binary-tree-level-order-traversal/

class TreeNode(var value: Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null
}

class BinaryTreeLevelOrderTraversalTest {
    private val root = TreeNode(5)

    private fun levelOrder(root: TreeNode?): List<List<Int>> {
        val result: MutableList<List<Int>> = mutableListOf()
        if (root == null) return result

        val queue = ArrayDeque<TreeNode>()
        queue.addLast(root)
        while (queue.isNotEmpty()) {
            val size = queue.size
            val list = mutableListOf<Int>()
            for (i in 0 until size) {
                val next = queue.removeFirst()
                list += next.value
                next.left?.also { queue.addLast(it) }
                next.right?.also { queue.addLast(it) }
            }
            result += list
        }
        return result
    }

    @Test
    fun testEmpty() {
        assertContentEquals(
            emptyList(),
            levelOrder(null)
        )
    }

    @Test
    fun testSingle() {
        println("levelOrder(ti) = ${levelOrder(root)}")
        assertContentEquals(
            listOf(listOf(5)),
            levelOrder(root)
        )
    }

    @Test
    fun testMore() {
        root.left = TreeNode(4)
        root.right = TreeNode(3)

        assertContentEquals(
            listOf(listOf(5), listOf(4,3)),
            levelOrder(root)
        )
    }
}