package interview

import kotlin.test.Test
import kotlin.test.assertEquals

class CaesarCipherTest {

    private fun String.encrypt(shift: Int): String =
        toCharArray().map {
            when {
                it.isUpperCase() -> 'A' + ((it + shift - 'A') % 26)
                it.isLowerCase() -> 'a' + ((it + shift - 'a') % 26)
                else -> it
            }
        }.joinToString("")

    private fun String.decrypt(shift: Int) = encrypt(-shift)

    @Test
    fun testEncrypt() {
        assertEquals("EXXEGOEXSRGI", "ATTACKATONCE".encrypt(4))
        assertEquals("bcd", "abc".encrypt(1))
    }

    @Test
    fun testDecrypt() {
        assertEquals("ATTACKATONCE", "EXXEGOEXSRGI".decrypt(4))
        assertEquals("abc", "bcd".decrypt(1))
    }
}