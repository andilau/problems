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
            .flatMap { listOf(it.first() to it.last(), it.last() to it.first()) }
            .groupBy({ it.first }, { it.second })
            .mapValues { it.value.toSet() }

    @Test
    fun testDistanceOfRelationships() {
        assertEquals(0, shortestPath(graph, 'a', 'a'))
        assertEquals(1, shortestPath(graph, 'a', 'b'))
        assertEquals(2, shortestPath(graph, 'a', 'c'))
        assertEquals(-1, shortestPath(graph, 'a', 'e'))
    }

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
}