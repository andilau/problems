import org.junit.Test
import kotlin.test.assertTrue

internal class `Anagram With Frequencies` {

    @Test
    internal fun `Valid Anagram`() {
        assertTrue { "danger".isAnagramOf("garden") }
        assertTrue { "nameless".isAnagramOf("salesmen") }
    }

    private fun String.isAnagramOf(other: String): Boolean =
        length == other.length
                && frequencies() == other.groupingBy { it }.eachCount()

    private fun String.frequencies() = groupingBy { it }.eachCount()
}

internal class `Anagram With Sorted` {
    @Test
    internal fun `Valid Anagram`() {
        assertTrue { "danger".isAnagramOf("garden") }
        assertTrue { "nameless".isAnagramOf("salesmen") }
    }

    private fun String.isAnagramOf(word: String): Boolean =
        length == word.length
                && toList().sorted() == word.toList().sorted()

}