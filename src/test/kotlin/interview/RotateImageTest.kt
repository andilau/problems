package interview

import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.Test

// 48. Rotate Image
// https://leetcode.com/problems/rotate-image/

class RotateImageTest {

    @Test
    fun shouldRotateRight() {
        val from = arrayOf(
            intArrayOf(1, 2, 3),
            intArrayOf(4, 5, 6),
            intArrayOf(7, 8, 9)
        )

        val to = arrayOf(
            intArrayOf(7, 4, 1),
            intArrayOf(8, 5, 2),
            intArrayOf(9, 6, 3)
        )
        from.rotate()
        assertArrayEquals(to, from)

    }

    private fun Array<IntArray>.rotate() {
        var left = 0
        var right = lastIndex
        while (left < right) {
            val top = left
            val bottom = right
            for (ix in 0 until size - 1) {
                val topLeft = this[top][left + ix]
                this[top][left + ix] = this[bottom - ix][left]
                this[bottom - ix][left] = this[bottom][right - ix]
                this[bottom][right - ix] = this[top + ix][right]
                this[top + ix][right] = topLeft
            }
            left++
            right--
        }
    }
}
