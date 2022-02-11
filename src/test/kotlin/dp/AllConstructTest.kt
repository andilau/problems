package dp

import org.junit.jupiter.api.Test
import kotlin.test.assertContentEquals

class AllConstructTest {
    private fun String.allConstruct(
        words: List<String>,
        memo: MutableMap<String, Set<List<String>>> = mutableMapOf()
    ): Collection<List<String>> {
        if (this in memo) return memo[this]!!
        if (isEmpty()) return listOf(emptyList())

        val result = mutableSetOf<List<String>>()
        for (word in words) {
            if (startsWith(word)) {
                result += substring(word.length)
                    .allConstruct(words, memo)
                    .map { list -> listOf(word) + list }
            }
        }
        memo[this] = result
        return result
    }

    @Test
    fun canConstructTest() {
        val expected = listOf(
            listOf("purp", "le"),
            listOf("p", "ur", "p", "le")
        )
        val actual = "purple".allConstruct(listOf("purp", "p", "ur", "le", "purpl"))
        println("actual = ${actual}")
        assertContentEquals(expected, "purple".allConstruct(listOf("purp", "p", "ur", "le", "purpl")))
    }

    @Test
    fun canConstructMultipleWaysTest() {
        val expected = listOf(
            listOf("ab", "cd", "ef"),
            listOf("ab", "c", "def"),
            listOf("abc", "def"),
            listOf("abcd", "ef")
        )
        assertContentEquals(
            expected, "abcdef".allConstruct(
                listOf("ab", "abc", "cd", "def", "abcd", "ef", "c")
            )
        )
    }

    @Test
    fun canConstructWorstTest() {
        val expected = setOf<List<String>>()
        assertContentEquals(
            expected, "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaz".allConstruct(
                listOf("a", "aa", "aaa", "aaaa", "aaaaa", "aaaaa")
            )
        )
    }
}