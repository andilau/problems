import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

@DisplayName("Fibonacci")
class FibonacciTest {
    private val fibonacci = listOf(0, 1, 1, 2, 3, 5, 8, 13)

    @Test
    fun `Fibonacci Naive`() {
        fun fibonacciNaive(n: Int): Int = when (n) {
            0 -> 0
            1 -> 1
            else -> fibonacciNaive(n - 1) + fibonacciNaive(n - 2)
        }
        assertEquals(fibonacciNaive(7), fibonacci[7])
    }

    @Test
    fun `Fibonacci With Tail Recursion`() {
        tailrec fun fibonacciWithTail(n: Int, a: Int = 0, b: Int = 1): Int = when (n) {
            0 -> a
            1 -> b
            else -> fibonacciWithTail(n - 1, b, a + b)
        }
        assertEquals(fibonacciWithTail(7), fibonacci[7])
    }

    @Test
    fun `Fibonacci With Fold`() {
        fun fibonacciWithFold(n: Int): Int =
            (0 until n).fold(0 to 1) { (f, s), _ -> s to f + s }.first
        assertEquals(fibonacciWithFold(7), fibonacci[7])
    }

    @Test
    fun `Fibonacci With Sequence`() {
        fun fibonacciWithSequence() = sequence {
            var pair = 0 to 1
            while (true)
                yield(pair.first).also { pair = pair.second to pair.first + pair.second }
        }
        assertEquals(fibonacciWithSequence().drop(7).first(), fibonacci[7])
    }
}