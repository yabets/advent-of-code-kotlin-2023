import kotlin.math.max
import kotlin.math.min

fun main() {
    fun isValidPart(board: List<List<Char>>, i: Int, start: Int, end: Int): Boolean {
        for(x in max(i - 1, 0).rangeTo(min(i + 1, board.size - 1))) {
            for(y in max(start - 1, 0).rangeTo(min(end + 1, board[i].size - 1))) {
                if(!board[x][y].isDigit() && board[x][y] != '.') {
                    return true
                }
            }
        }
        return false
    }

    fun part1(input: List<String>): Int {
        val board = input.stream().map { it.toList() }.toList()
        var result = 0
        for(i in 0.rangeTo(board.size - 1)) {
            var j = 0
            while(j < board[i].size ) {
                if(board[i][j].isDigit()) {
                    val start = j
                    var number = board[i][j].toString()
                    // go to the end of number
                    while(j < board[i].size - 1 && board[i][j + 1].isDigit()) {
                        number += board[i][j + 1]
                        j++
                    }
                    val end = j
                    if(isValidPart(board, i, start, end)) {
                        result += number.toInt()
                    }
                }
                j++
            }
        }
        return result
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day03_test")
    part1(testInput).println()
    check(part1(testInput) == 4361)

    val input = readInput("Day03")
    part1(input).println()
    part2(input).println()

}

