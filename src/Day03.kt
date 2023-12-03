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
        for(i in 0..<board.size) {
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

    fun getNumber(board: List<List<Char>>, x: Int, y: Int): Pair<Int,Pair<Int,Int>> {
        var start = y
        var end = y
        // find start of number
        while (start > 0 && board[x][start - 1].isDigit()) {
            start--
        }
        // find end of number
        while(end < board[x].size - 1 && board[x][end + 1].isDigit()) {
            end++
        }
        val number = start.rangeTo(end).map { board[x][it] }.joinToString("").toInt()
        return Pair(number, Pair(x, start))
    }

    fun findAdjacentNumbersPower(board: List<List<Char>>, i: Int, j: Int): Int {
        val possibleNumbers = mutableSetOf<Pair<Int,Pair<Int, Int>>>()
        for(x in max(i - 1, 0).rangeTo(min(i + 1, board.size - 1))) {
            for(y in max(j - 1, 0).rangeTo(min(j + 1, board[i].size - 1))) {
                if(board[x][y].isDigit()) {
                    // get number
                    val number = getNumber(board, x, y)
                    possibleNumbers.add(number)
                }
            }
        }
        if(possibleNumbers.size != 2) {
            return 0
        }

        return possibleNumbers.first().first * possibleNumbers.last().first
    }

    fun part2(input: List<String>): Int {
        val board = input.stream().map { it.toList() }.toList()
        var result = 0
        for(i in 0..<board.size) {
            var j = 0
            while(j < board[i].size ) {
                if(board[i][j] == '*') {
                    result += findAdjacentNumbersPower(board, i, j)
                }
                j++
            }
        }

        return result
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day03_test")
    check(part2(testInput) == 467835)

    val input = readInput("Day03")
    part1(input).println()
    part2(input).println()

}

