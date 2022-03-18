package leetcode

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import java.lang.Integer.max
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals

// 105. Construct Binary Tree from Preorder and Inorder Traversal
// https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/

@DisplayName("Construct Binary Tree from Preorder and Inorder Traversal")
class ConstructBinaryTreePreorderInorderTest {

    data class Node<T>(
        val value: T,
        var left: Node<T>? = null,
        var right: Node<T>? = null
    )

    private fun <T> constructTree(preorder: List<T>, inorder: List<T>): Node<T>? {
        if (preorder.isEmpty() || inorder.isEmpty()) return null

        val root = Node(preorder.first())
        val mid = inorder.indexOf(preorder.first())
        root.left = constructTree(preorder.subList(1, mid + 1), inorder.subList(0, mid))
        root.right = constructTree(preorder.subList(mid + 1, preorder.size), inorder.subList(mid + 1, inorder.size))
        return root
    }

    private val preOrder = listOf(3, 9, 20, 15, 7)
    private val inOrder = listOf(9, 3, 15, 20, 7)

    @Test
    fun `Should min to 3`() {
        println("constructTree(preOrder, inOrder) = ${constructTree(preOrder, inOrder)}")
        val treeToList: List<Int?> = constructTree(preOrder, inOrder)!!.treeToList()
        println("treeToList = ${treeToList}")
        val expected: List<Int?> = listOf(3, 9, 20, null, null, 15, 7)
        assertContentEquals(expected, treeToList)
    }

    @Test
    fun `test depth`() {
        fun <T> Node<T>.depth(depth: Int = 1): Int = max(
            this.left?.depth(depth + 1) ?: depth,
            this.right?.depth(depth + 1) ?: depth
        )

        val depth = constructTree(preOrder, inOrder)!!.depth()
        assertEquals(3, depth)
    }

    private fun <T> Node<T>.treeToList(): List<T?> {
        val result = mutableListOf<T?>()
        //val depth = this.depth()
        val queue = ArrayDeque<Node<T>>()
        queue.addLast(this)
        while (queue.isNotEmpty()) {
            val next = queue.removeFirst()
            result += next.value
            if (next.left != null) queue.addLast(next.left!!)
            if (next.right != null) queue.addLast(next.right!!)
        }
        return result
    }
    // Output: [3,9,20,null,null,15,7]

    @Test
    fun `Should min to -1`() {
        assertContentEquals(listOf(-1), constructTree(listOf(-1), listOf(-1))!!.treeToList())
    }
}