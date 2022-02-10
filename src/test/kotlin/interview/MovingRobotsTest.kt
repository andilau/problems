package interview

import org.junit.jupiter.api.Test
import kotlin.math.abs
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class MovingRobotsTest {

    private fun isValid(grid: Array<IntArray>): Boolean {
        for (i in 0 until grid.lastIndex) {
            if (!isValidTransition(grid[i], grid[i + 1])) return false
        }
        return true
    }

    private fun isValidTransition(from: IntArray, to: IntArray): Boolean {
        if (from.size != to.size) return false
        if (from.count { it == 1 } != to.count { it == 1 }) return false
        val fromIndices = from.toList().withIndex().filter { it.value == 1 }.map { it.index }
        val toIndices = to.toList().withIndex().filter { it.value == 1 }.map { it.index }
        for (ix in fromIndices.indices) {
            if (abs(fromIndices[ix] - toIndices[ix]) > 1) return false
        }
        return true
    }

    @Test
    fun `robot count is equal but not valid`() {
        val input = arrayOf(
            intArrayOf(1, 0, 0, 1),
            intArrayOf(1, 1, 0, 0)
        )
        assertFalse { isValid(input) }
    }

    @Test
    fun `robot count is equal and also valid`() {
        val input = arrayOf(
            intArrayOf(1, 0, 0, 1),
            intArrayOf(1, 0, 1, 0)
        )
        assertTrue { isValid(input) }
    }
}

