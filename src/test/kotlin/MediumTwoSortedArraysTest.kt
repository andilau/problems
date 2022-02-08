import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class MediumTwoSortedArraysTest {

    @Test
    fun shouldOutput5() {
        val array1 = intArrayOf(1, 9, 15)
        val array2 = intArrayOf(2, 4, 5, 23)
        assertEquals(5.0, mediumOf(array1, array2))
    }

    @Test
    fun shouldOutput7_5() {
        val array1 = intArrayOf(41, 78)
        val array2 = intArrayOf(3, 4, 7, 8)
        assertEquals(7.5, mediumOf(array1, array2))
    }

    private fun mediumOf(array1: IntArray, array2: IntArray): Double {
        with((array1 + array2).sorted()) {
            return@mediumOf if (size % 2 == 1)
                this[size / 2].toDouble()
            else
                (this[size / 2 - 1] + this[size / 2]) / 2.0
        }
    }
}