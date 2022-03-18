package leetcode

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals

// 12. Integer to Roman
// https://leetcode.com/problems/integer-to-roman/

class IntegerToRomanTest {
    private val map = mapOf(
        1 to "I",
        4 to "IV",
        5 to "V",
        9 to "IX",
        10 to "X",
        40 to "XL",
        50 to "L",
        90 to "XC",
        100 to "C",
        400 to "CD",
        500 to "D",
        900 to "CM",
        1000 to "M"
    )

    private fun Int.toRoman(): String {
        val result = StringBuilder()
        if (this <= 0) throw IllegalArgumentException()

        var remainder = this
        for (e in map.toSortedMap(compareByDescending { it })) {
            while (remainder > 0) {
                if (remainder < e.key) break
                result.append(e.value)
                remainder -= e.key
            }
        }
        return result.toString()
    }

    @Test
    fun testZero() {
        assertThrows<IllegalArgumentException> { 0.toRoman() }
    }

    @Test
    fun testThree() {
        assertEquals("III", 3.toRoman())
    }

    @Test
    fun test58() {
        assertEquals("LVIII", 58.toRoman())
    }

    @Test
    fun test1994() {
        assertEquals("MCMXCIV", 1994.toRoman())
    }

    @Test
    fun testMax() {
        assertEquals("MMMCMXCIX", 3999.toRoman())
    }
}