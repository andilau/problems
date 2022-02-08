package dp

import org.junit.jupiter.api.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class CanSumTest {
    @Test
    fun canSumTest() {
        assertTrue { canSum(7, intArrayOf(2, 3)) }
        assertFalse { canSum(7, intArrayOf(2, 4)) }
    }

    @Test
    fun canSum300Test() {
        assertFalse { canSum(300, intArrayOf(7, 14)) }
    }

    private fun canSum(
        target: Int,
        numbers: IntArray,
        memo: MutableMap<Int, Boolean> = mutableMapOf()
    ): Boolean {
        if (target in memo) return memo[target]!!
        if (target == 0) return true
        if (target < 0) return false

        return numbers.any { number ->
            val remainder = target - number
            val can = canSum(remainder, numbers, memo)
            memo[target] = can
            can
        }
    }
    // Base
    // Time: O(n^m)
    // Space: O(m)

    // Improved
    // Time:  O(m * n)
    // Space: O(m)
}