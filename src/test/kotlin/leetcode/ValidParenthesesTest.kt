package leetcode

import org.junit.jupiter.api.Test
import java.util.*
import kotlin.test.assertFalse
import kotlin.test.assertTrue

// 20. Valid Parentheses
// https://leetcode.com/problems/valid-parentheses/

class ValidParenthesesTest {
    private fun String.isValid(): Boolean {
        val stack = Stack<Char>()
        forEach {
            when (it) {
                in "[({" -> stack.push(it)
                ')' -> if (stack.pop() != '(') return false
                ']' -> if (stack.pop() != '[') return false
                '}' -> if (stack.pop() != '{') return false
                else -> error("Invalid input: $it")
            }
        }
        return true
    }

    @Test
    fun simpleTest() {
        assertTrue { "()".isValid() }
    }

    @Test
    fun simpleTestAllPairs() {
        assertTrue { "()[]{}".isValid() }
    }

    @Test
    fun simpleTestNotMatching() {
        assertFalse { "(]".isValid() }
    }

}