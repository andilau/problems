package leetcode

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

// 200. Number of Islands
// https://leetcode.com/problems/number-of-islands/

class CountIslandsTest {

    private fun numIslands(grid: Array<CharArray>): Int {
        val visited = mutableSetOf<Pair<Int, Int>>()
        fun traverse(x: Int, y: Int): Boolean {
            if (x < 0 || x > grid.first().lastIndex) return false
            if (y < 0 || y > grid.lastIndex) return false
            if (grid[y][x] == '0') return false
            if (x to y in visited) return false
            visited += x to y
            traverse(x - 1, y)
            traverse(x + 1, y)
            traverse(x, y - 1)
            traverse(x, y + 1)
            return true
        }

        var count = 0
        for (y in grid.indices) for (x in grid.first().indices)
            if (traverse(x, y)) count += 1
        return count
    }

    @Test
    fun testOneIsland() {
        val gridWithOneIsland = arrayOf(
            charArrayOf('1', '1', '1', '1', '0'),
            charArrayOf('1', '1', '0', '1', '0'),
            charArrayOf('1', '1', '0', '0', '0'),
            charArrayOf('0', '0', '0', '0', '0')
        )
        assertEquals(1, numIslands(gridWithOneIsland))
    }

    @Test
    fun testThreeIslands() {
        val gridWithThreeIslands = arrayOf(
            charArrayOf('1', '1', '0', '0', '0'),
            charArrayOf('1', '1', '0', '0', '0'),
            charArrayOf('0', '0', '1', '0', '0'),
            charArrayOf('0', '0', '0', '1', '1')
        )
        assertEquals(3, numIslands(gridWithThreeIslands))
    }


}