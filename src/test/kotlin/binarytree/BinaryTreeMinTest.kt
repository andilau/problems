package binarytree

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import java.lang.Integer.min
import kotlin.test.assertEquals

@DisplayName("Binary Tree Min")
class BinaryTreeMinTest {
    data class Node<T>(val value: T, var left: Node<T>? = null, var right: Node<T>? = null)

    val a = Node(5)
    val b = Node(11)
    val c = Node(3)
    val d = Node(4)
    val e = Node(15)
    val f = Node(12)

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
        fun `Should min to 3`() {
            fun minOfTree(node: Node<Int>?): Int {
                if (node == null) return Int.MAX_VALUE
                return min(
                    node.value,
                    min(minOfTree(node.left), minOfTree(node.right))
                )
            }
            assertEquals(3, minOfTree(a))
        }
    }

    @Nested
    @DisplayName("Breadth First")
    inner class BreadthFirst {
        @Test
        fun `Should min to 3`() {
            fun minOfTree(node: Node<Int>?): Int {
                if (node == null) return Int.MAX_VALUE
                var min = Int.MAX_VALUE
                val queue = ArrayDeque<Node<Int>>()
                    .apply { addLast(node) }

                while (queue.isNotEmpty()) {
                    val next = queue.removeFirst()
                    min = min(min, next.value)
                    next.left?.also { queue.addLast(it) }
                    next.right?.also { queue.addLast(it) }
                }
                return min
            }
            assertEquals(3, minOfTree(a))
        }
    }
}