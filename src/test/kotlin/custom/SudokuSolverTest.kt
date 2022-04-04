package custom

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import kotlin.test.assertFalse

class SudokuSolverTest {
    private val board = arrayOf(
        intArrayOf(0, 0, 0, 0, 0, 0, 0, 3, 0),
        intArrayOf(0, 3, 1, 2, 0, 0, 0, 0, 0),
        intArrayOf(5, 9, 0, 0, 0, 7, 0, 6, 0),
        intArrayOf(3, 0, 0, 0, 0, 0, 0, 0, 7),
        intArrayOf(9, 4, 0, 0, 2, 1, 3, 8, 0),
        intArrayOf(0, 8, 0, 0, 6, 0, 4, 2, 0),
        intArrayOf(8, 2, 0, 0, 1, 0, 5, 0, 0),
        intArrayOf(0, 0, 0, 9, 0, 2, 0, 0, 0),
        intArrayOf(6, 0, 0, 5, 0, 8, 0, 1, 0),
    )

    private val expected = arrayOf(
        intArrayOf(2, 6, 7, 1, 8, 5, 9, 3, 4),
        intArrayOf(4, 3, 1, 2, 9, 6, 7, 5, 8),
        intArrayOf(5, 9, 8, 4, 3, 7, 1, 6, 2),
        intArrayOf(3, 1, 2, 8, 5, 4, 6, 9, 7),
        intArrayOf(9, 4, 6, 7, 2, 1, 3, 8, 5),
        intArrayOf(7, 8, 5, 3, 6, 9, 4, 2, 1),
        intArrayOf(8, 2, 4, 6, 1, 3, 5, 7, 9),
        intArrayOf(1, 5, 3, 9, 7, 2, 8, 4, 6),
        intArrayOf(6, 7, 9, 5, 4, 8, 2, 1, 3),
    )

    @Test
    fun `sudoku board should not be equal`() {
        assertThat(board).isNotEqualTo(expected)
    }

    @Test
    fun `sudoku board should not be solvable`() {
        board[0][0] = 1
        assertFalse(board.solve())
    }

    @Test
    fun `sudoku board should be equal`() {
        board.solve()
        assertThat(board).isDeepEqualTo(expected)
    }

    private fun Array<IntArray>.solve(): Boolean {

        fun Array<IntArray>.isValidRow(index: Int, candidate: Int): Boolean {
            this[index].forEach { if (it == candidate) return false }
            return true
        }

        fun Array<IntArray>.isValidColumn(index: Int, candidate: Int): Boolean {
            this.forEach { if (it[index] == candidate) return false }
            return true
        }

        fun Array<IntArray>.isValidBox(rowIndex: Int, columnIndex: Int, candidate: Int): Boolean {
            val rowBox = rowIndex - rowIndex % 3
            val columnBox = columnIndex - columnIndex % 3

            for (i in rowBox until rowBox + 3) for (j in columnBox until columnBox + 3) {
                if (this[i][j] == candidate) return false
            }
            return true
        }

        fun Array<IntArray>.isValidCandidate(rowIndex: Int, columnIndex: Int, candidate: Int): Boolean =
            isValidRow(rowIndex, candidate) &&
                    isValidColumn(columnIndex, candidate) &&
                    isValidBox(rowIndex, columnIndex, candidate)

        for (row in 0 until 9) for (column in 0 until 9) {
            if (this[row][column] == 0) {
                for (candidate in 1..9) {
                    if (this.isValidCandidate(row, column, candidate)) {
                        check(row < 9)
                        check(column < 9)
                        this[row][column] = candidate
                        if (this.solve())
                            return true
                        else
                            this[row][column] = 0

                    }
                }
                return false
            }
        }
        return true
    }

    private fun Array<IntArray>.print() {
        forEachIndexed() { index, row ->
            if (index != 0 && index % 3 == 0) println("---|---|---")
            val joinToString = row.toList().chunked(3).joinToString("|") { it.joinToString("") }
            println(joinToString)
        }
    }

}


