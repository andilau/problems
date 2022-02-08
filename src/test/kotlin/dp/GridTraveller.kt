package dp

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class GridTraveller {

    @Test
    fun test() {
        fun gridTraveller(row: Int, col: Int, memo: MutableMap<Pair<Int, Int>, Long> = mutableMapOf()): Long {
            val key = row to col
            memo[key]?.also { return it }
            if (row == 1 || col == 1) return 1
            if (row == 0 || col == 0) return 0
            memo[key] = gridTraveller(row - 1, col, memo) + gridTraveller(row, col - 1, memo)
            memo[key.copy(key.second, key.first)] = memo[key]!!
            return memo[key]!!
        }
        assertEquals(1, gridTraveller(100, 1))
        assertEquals(100, gridTraveller(100, 2))
        assertEquals(3, gridTraveller(3, 2))
        assertEquals(6, gridTraveller(3, 3))
        assertEquals(2333606220, gridTraveller(18, 18))
        assertEquals(4631081169483718960, gridTraveller(100, 100))
    }

}