package dp

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class FibonacciDPTest {

    private fun fibonacciWithMemo(n: Int, memo: MutableMap<Int, Int> = mutableMapOf()): Int {
        memo[n]?.also { return it }
        if (n <= 2) return 1
        memo[n] = fibonacciWithMemo(n - 1, memo) + fibonacciWithMemo(n - 2, memo)
        return memo[n]!!
    }

    @Test
    fun fibonacci_6() {
        assertEquals(8, fibonacciWithMemo(6))
    }

    @Test
    fun fibonacci_40() {
        assertEquals(102334155, fibonacciWithMemo(40))
    }
}
