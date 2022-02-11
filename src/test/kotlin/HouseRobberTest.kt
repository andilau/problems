import org.junit.jupiter.api.Test
import kotlin.math.max
import kotlin.test.assertEquals

// 198. House Robber
// https://leetcode.com/problems/house-robber/

class HouseRobberTest {

    @Test
    fun `maximum amount of money you can rob tonight without alerting the police is 4`() {
        assertEquals(4, listOf(1, 2, 3, 1).rob())
    }

    @Test
    fun `maximum amount of money you can rob tonight without alerting the police is 12`() {
        assertEquals(12, listOf(2, 7, 9, 3, 1).rob())
    }
}

private fun List<Int>.rob(): Int {
    if (size == 0) return 0
    if (size == 1) return this[0]

    val dp = IntArray(size)

    dp[0] = this[0]
    dp[1] = max(this[0], this[1])

    for (i in 2 until size)
        dp[i] = max(dp[i - 1], dp[i - 2] + this[i])

    return dp.last()
}