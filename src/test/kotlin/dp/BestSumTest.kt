package dp

import org.junit.jupiter.api.Test
import kotlin.test.assertContentEquals

class BestSumTest {
    private fun bestSum(
        target: Int,
        numbers: IntArray,
        memo: MutableMap<Int, IntArray?> = mutableMapOf()
    ): IntArray? {
        if (target in memo) return memo[target]
        if (target == 0) return intArrayOf()
        if (target < 0) return null

        val shortest =
            numbers
                .map { number -> number to target - number }
                .mapNotNull { (number, remainder) -> bestSum(remainder, numbers, memo)?.plus(number) }
                .minByOrNull { it.size }

        return (shortest).also { memo[target] = it }

        /*
        var shortest: IntArray? = null

        for (number in numbers) {
            val remainder = target - number
            val array = bestSum(remainder, numbers, memo)
            if (array != null) {
                val combination = array + number
                if (combination.size < (shortest?.size ?: Int.MAX_VALUE))
                    shortest = combination
            }
        }
     */
    }

    @Test
    fun test() {
        assertContentEquals(intArrayOf(7), bestSum(7, intArrayOf(5, 3, 4, 7)))
        assertContentEquals(intArrayOf(5, 3), bestSum(8, intArrayOf(2, 3, 5)))
        assertContentEquals(intArrayOf(4, 4), bestSum(8, intArrayOf(1, 4, 5)))
        assertContentEquals(intArrayOf(25, 25, 25, 25), bestSum(100, intArrayOf(1, 2, 5, 25)))
    }
}