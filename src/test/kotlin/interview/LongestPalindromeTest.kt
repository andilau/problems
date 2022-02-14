package interview

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

// 409. Longest Palindrome
// https://leetcode.com/problems/longest-palindrome/

class LongestPalindromeTest {
    private fun String.longestPalindrome(): Int =
        groupingBy { it }.eachCount()
            .values
            .map { count -> if (count % 2 == 0) count else count - 1 }
            .sum()
            .let { sum -> if (length == sum) sum else sum + 1 }

    @Test
    fun testOneLetter() {
        assertEquals(1, "a".longestPalindrome())
    }

    @Test
    fun testTwoIdenticalLetters() {
        assertEquals(2, "bb".longestPalindrome())
    }

    @Test
    fun testExample() {
        assertEquals(7, "abccccdd".longestPalindrome())
    }

    @Test
    fun testCounterExample() {
        assertEquals(5, "abcccdd".longestPalindrome())
    }
}