package dp

import org.junit.jupiter.api.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class CanConstructTest {
    fun canConstruct(
        target: String,
        prefixes: List<String>,
        memo: MutableMap<String, Boolean> = mutableMapOf()
    ): Boolean {
        if (target in memo) return memo[target]!!
        if (target.isEmpty()) return true

        for (prefix in prefixes) {
            if (target.startsWith(prefix)) {
                val rem = target.substring(prefix.length)
                if (canConstruct(rem, prefixes,memo)) {
                    memo[target] = true
                    return true
                }
            }
        }
        memo[target] = false
        return false
    }

    @Test
    fun test() {
        assertTrue { canConstruct("foobar", listOf("foo", "bar")) }
        assertTrue { canConstruct("abcdef", listOf("ab", "abc", "cd", "def", "abcd")) }
        assertFalse {
            canConstruct(
                "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeef",
                listOf("e", "ee", "eee", "eeee", "eeeee", "eeeeee")
            )
        }
    }
}