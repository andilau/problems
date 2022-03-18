package leetcode

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals

// 13. Roman to Integer
// https://leetcode.com/problems/roman-to-integer/description/

@DisplayName("Roman To Integer")
class RomanToIntegerTest {

    /*
    * Symbol       Value
    I             1
    V             5
    X             10
    L             50
    C             100
    D             500
    M             1000
    */

    private fun String.romanToInt(): Int =
        map { roman ->
            when (roman) {
                'I' -> 1
                'V' -> 5
                'X' -> 10
                'L' -> 50
                'C' -> 100
                'D' -> 500
                'M' -> 1000
                else -> throw IllegalArgumentException("Not in alphabet: $roman")
            }
        }
            .windowed(2, 1, true)
            .fold(0) { sum, pair ->
                if (pair.size == 1) sum + pair.first()
                else if (pair[0] < pair[1]) sum - pair[0]
                else sum + pair[0]
            }

    @Test
    fun `invalid roman should throw IllegalArgumentException`() {
        assertThrows<IllegalArgumentException> { "ABC".romanToInt() }
    }

    @Test
    fun `empty roman should convert to 0`() {
        assertEquals(0, "".romanToInt())
    }

    @Test
    fun `roman I should convert to 1`() {
        assertEquals(1, "I".romanToInt())
    }

    @Test
    fun `roman III should convert to 3`() {
        assertEquals(3, "III".romanToInt())
    }

    @Test
    fun `roman IV should convert to 6`() {
        assertEquals(6, "VI".romanToInt())
    }

    @Test
    fun `roman To Integer Test`() {
        assertEquals(1, "I".romanToInt())
        assertEquals(2, "II".romanToInt())
        assertEquals(3, "III".romanToInt())
        assertEquals(4, "IV".romanToInt())
        assertEquals(5, "V".romanToInt())
        assertEquals(6, "VI".romanToInt())
        assertEquals(7, "VII".romanToInt())
        assertEquals(8, "VIII".romanToInt())
        assertEquals(9, "IX".romanToInt())
        assertEquals(10, "X".romanToInt())
        assertEquals(40, "XL".romanToInt())
        assertEquals(60, "LX".romanToInt())
        assertEquals(90, "XC".romanToInt())
        assertEquals(110, "CX".romanToInt())
        assertEquals(900, "CM".romanToInt())
        assertEquals(1100, "MC".romanToInt())
    }
}