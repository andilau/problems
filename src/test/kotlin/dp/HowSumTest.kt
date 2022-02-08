package dp

import org.junit.jupiter.api.Test
import kotlin.test.assertContentEquals

class HowSumTest {

    private fun howSum(
        target: Int,
        numbers: IntArray,
        memo: MutableMap<Int, IntArray?> = mutableMapOf()
    ): IntArray? {
        if (target in memo) return memo[target]
        if (target == 0) return intArrayOf()
        if (target < 0) return null
        for (number in numbers) {
            val remainer = target - number
            val array = howSum(remainer, numbers, memo)
            if (array != null) {
                memo[target] = array + number
                return memo[target]
            }
        }
        memo[target] = null
        return memo[target]
    }

    @Test
    fun howsum() {
        assertContentEquals(intArrayOf(1, 1, 1, 1, 1, 1, 1), howSum(7, intArrayOf(1, 2, 3)))
        assertContentEquals(null, howSum(7, intArrayOf(9, 8, 3)))
        assertContentEquals(null, howSum(7, intArrayOf(9, 8, 3)))
        assertContentEquals(null, howSum(300, intArrayOf(7, 14)))
    }

}