import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

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

private fun List<Int>.rob(start: Int = 0): Int {
    var result = 0
    for (i in start..lastIndex) {
        result += this.rob(i)
    }
    return result
}
