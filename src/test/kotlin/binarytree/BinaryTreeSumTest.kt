package binarytree

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

@DisplayName("Binary Tree Sum")
class BinaryTreeSumTest {
    data class Node<T>(val value: T, var left: Node<T>? = null, var right: Node<T>? = null)

    val a = Node(3)
    val b = Node(11)
    val c = Node(4)
    val d = Node(4)
    val e = Node(2)
    val f = Node(1)

    init {
        a.left = b
        a.right = c
        b.left = d
        b.right = e
        c.right = f
    }

    @Nested
    @DisplayName("Depth First")
    inner class DepthFirst {
        @Test
        fun `Should sum up to 25`() {
            fun sumOfTree(node: Node<Int>?): Int {
                if (node == null) return 0
                return node.value + sumOfTree(node.left) + sumOfTree(node.right)
            }
            assertEquals(25, sumOfTree(a))
        }
    }

    @Nested
    @DisplayName("Breadth First")
    inner class BreadthFirst {
        @Test
        fun `Should sum up to 25`() {
            fun sumOfTree(node: Node<Int>?): Int {
                if (node == null) return 0
                var result = 0
                val queue = ArrayDeque<Node<Int>>()
                queue.addLast(node)
                while (queue.isNotEmpty()) {
                    val next = queue.removeFirst()
                    result += next.value
                    if (next.left != null) queue.addLast(next.left!!)
                    if (next.right != null) queue.addLast(next.right!!)
                }
                return result
            }
            assertEquals(25, sumOfTree(a))
        }
    }
}