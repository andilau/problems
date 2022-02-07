package binarytree

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import java.util.*
import kotlin.test.assertContentEquals

// Tree Nodes point to point edges
// -terms values
// -parent->child relationship
// -root (no parent)
// -leaf (no children)
// -binary
//  -at most 2 children
//  -exactly one root
//  -exactly one single path


class BinaryTreeTest {
    data class Node<T>(val value: T, var left: Node<T>? = null, var right: Node<T>? = null)

    val a = Node('A')
    val b = Node('B')
    val c = Node('C')
    val d = Node('D')
    val e = Node('E')
    val f = Node('F')

    init {
        a.left = b
        a.right = c
        b.left = d
        b.right = e
        c.right = f

        /*
        a
       / \
      b   c
     / \   \
    d   e   f
        * */
    }

    @Test
    fun testOutputDF() {
        assertDoesNotThrow { depthFirstTraversal(a) }
        assertContentEquals(listOf('A', 'B', 'D', 'E', 'C', 'F'), depthFirstTraversal(a))
    }

    @Test
    fun testOutputDFRecursive() {
        assertDoesNotThrow { depthFirstTraversalRecursive(a) }
        assertContentEquals(listOf('A', 'B', 'D', 'E', 'C', 'F'), depthFirstTraversalRecursive(a))
    }

    @Test
    fun testOutputBF() {
        assertDoesNotThrow { breadthFirstTraversal(a) }
        assertContentEquals(listOf('A', 'B', 'C', 'D', 'E', 'F'), breadthFirstTraversal(a))
    }

    @Test
    fun testOutputBFIncludes() {
        assertDoesNotThrow { breadthFirstTraversalIncludes(a) { it == 'C' } }
        assertTrue { breadthFirstTraversalIncludes(a) { it == 'C' } }
        assertFalse { breadthFirstTraversalIncludes(a) { it == 'Z' } }
    }

    @Test
    fun testOutputDFIncludes() {
        assertDoesNotThrow { depthFirstTraversalRecursiveIncludes(a) { it == 'C' } }
        assertTrue { depthFirstTraversalRecursiveIncludes(a) { it == 'C' } }
        assertFalse { depthFirstTraversalRecursiveIncludes(a) { it == 'Z' } }
    }

    private fun <T> depthFirstTraversalRecursiveIncludes(node: Node<T>?, predicate: (T) -> Boolean): Boolean {
        if (node == null) return false
        if (predicate.invoke(node.value)) return true
        return depthFirstTraversalRecursiveIncludes(node.left, predicate) ||
                depthFirstTraversalRecursiveIncludes(node.right, predicate)

    }

    private fun <T> breadthFirstTraversalIncludes(node: Node<T>?, predicate: (T) -> Boolean): Boolean {
        if (node == null) return false
        val queue = LinkedList<Node<T>>().apply { addLast(node) }
        while (queue.isNotEmpty()) {
            val next = queue.removeFirst()
            if (predicate.invoke(next.value)) return true
            if (next.left != null) queue.addLast(next.left)
            if (next.right != null) queue.addLast(next.right)
        }
        return false
    }

    private fun <T> breadthFirstTraversal(node: Node<T>?): Iterable<T> {
        if (node == null) return emptyList()
        val result = mutableListOf<T>()
        val queue = LinkedList<Node<T>>().apply { addLast(node) }
        while (queue.isNotEmpty()) {
            val next = queue.removeFirst()
            result += next.value
            if (next.left != null) queue.addLast(next.left)
            if (next.right != null) queue.addLast(next.right)
        }
        return result
    }

    private fun <T> depthFirstTraversal(node: Node<T>): Collection<T> {
        val result = mutableListOf<T>()
        val stack = LinkedList<Node<T>>()
        stack.addFirst(node)
        while (stack.isNotEmpty()) {
            val (value, left, right) = stack.removeFirst()
            print("$value ")
            result.add(value)
            if (right != null) stack.addFirst(right)
            if (left != null) stack.addFirst(left)
        }
        return result
    }

    private fun <T> depthFirstTraversalRecursive(node: Node<T>?): Collection<T> {
        if (node == null) return emptyList()
        val leftTree = depthFirstTraversalRecursive(node.left)
        val rightTree = depthFirstTraversalRecursive(node.right)
        return listOf(node.value) + leftTree + rightTree
    }
}

