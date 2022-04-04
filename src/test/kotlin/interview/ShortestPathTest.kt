package interview

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.util.*
import kotlin.test.assertEquals

class ShortestPathTest {
    private val graph: Map<Char, Set<Char>> =
        arrayOf("a-b-c-d", "a-m-n", "x-e-g-f")
            .flatMap { stringOfFriends ->
                stringOfFriends
                    .split('-')
                    .zipWithNext()
                    .map { it.first.plus(it.second) }
            }
            .also { println(it) }
            .flatMap { listOf(it.first() to it.last(), it.last() to it.first()) }
            .also { println(it) }
            .groupBy({ it.first }, { it.second })
            .also { println(it) }
            .mapValues { it.value.toSet() }

    private fun shortestPath(graph: Map<Char, Set<Char>>, from: Char, to: Char): Int {
        val visited = mutableSetOf<Char>()
        val fromNode = IndexedValue(0, from)
        val queue = LinkedList<IndexedValue<Char>>()
        queue.addLast(fromNode)
        while (queue.isNotEmpty()) {
            val (index, value) = queue.removeFirst()
            if (value in visited) continue
            visited += value

            if (value == to) return index
            graph[value]?.forEach { queue.addLast(IndexedValue(index + 1, it)) }
        }
        return -1
    }

    @Test
    fun testGraphAdjacent() {
        assertThat(graph)
            .isNotEmpty
            .containsEntry('a', "bm".toSet())
            .containsEntry('b', "ac".toSet())
            .containsEntry('c', "bd".toSet())
            .containsEntry('d', "c".toSet())
            .doesNotContainKey('z')
            .hasSize(10)
    }

    @Test
    fun testDistanceOfRelationshipsFromA() {
        assertEquals(0, shortestPath(graph, 'a', 'a'))
        assertEquals(1, shortestPath(graph, 'a', 'b'))
        assertEquals(2, shortestPath(graph, 'a', 'c'))
        assertEquals(-1, shortestPath(graph, 'a', 'e'))
    }

    @Test
    fun testDistanceOfRelationshipsFromB() {
        assertEquals(1, shortestPath(graph, 'b', 'a'))
        assertEquals(0, shortestPath(graph, 'b', 'b'))
        assertEquals(1, shortestPath(graph, 'b', 'c'))
        assertEquals(-1, shortestPath(graph, 'b', 'e'))
    }
}