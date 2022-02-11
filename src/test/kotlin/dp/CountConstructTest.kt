package dp

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class CountConstructTest {
    private fun String.countConstruct(wordbank: List<String>, memo: MutableMap<String, Int> = mutableMapOf()): Int {
        if (this.isEmpty()) return 1
        if (this in memo) return memo[this]!!

        var result = 0
        for (word in wordbank) {
            if (this.startsWith(word))
                result += this.substring(word.length).countConstruct(wordbank,memo)
        }
        memo[this] = result
        return result
    }

    @Test
    fun canConstructTest() {
        assertEquals(2, "purple".countConstruct(listOf("purp", "p", "ur", "le", "purpl")))
        assertEquals(1, "abcdef".countConstruct(listOf("ab", "abc", "cd", "def", "abcd")))
        assertEquals(
            0,
            "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeef".countConstruct(
                listOf("e", "ee", "eee", "eeee", "eeeee", "eeeeee")
            )
        )
    }
}