import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class FibonacciTest {
    private val fibonacci = listOf(0, 1, 1, 2, 3, 5, 8, 13)

    private fun fibonacciNaive(n: Int): Int = when (n) {
        0 -> 0
        1 -> 1
        else -> fibonacciNaive(n - 1) + fibonacciNaive(n - 2)
    }

    private tailrec fun fibonacciWithTail(n: Int, a: Int = 0, b: Int = 1): Int = when (n) {
        0 -> a
        1 -> b
        else -> fibonacciWithTail(n - 1, b, a + b)
    }

    private fun fibonacciWithFold(n: Int): Int =
        (0 until n).fold(0 to 1) { (f, s), _ -> s to f + s }.first

    private fun fibonacciWithSequence() = sequence {
        var pair = 0 to 1
        while (true)
            yield(pair.first).also { pair = pair.second to pair.first + pair.second }
    }

    @Test
    fun testFibonacciNaive() {
        assertEquals(fibonacciNaive(7), fibonacci[7])
    }

    @Test
    fun testFibonacciWithTail() {
        assertEquals(fibonacciWithTail(7), fibonacci[7])
    }

    @Test
    fun testFibonacciWithFold() {
        assertEquals(fibonacciWithFold(7), fibonacci[7])
    }

    @Test
    fun testFibonacciWithSequence() {
        assertEquals(fibonacciWithSequence().drop(7).first(), fibonacci[7])
    }
}