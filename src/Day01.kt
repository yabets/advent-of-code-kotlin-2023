import kotlin.streams.toList

fun main() {
    fun part1(input: List<String>): Int {
        return input.sumOf { s -> val digits = s.chars()
            .filter(Character::isDigit)
            .map{c -> c - 48}
            .toList()

            val first = digits.first().toInt()
            val second = digits.last().toInt()

             first * 10 + second
        }
    }

    fun part2(input: List<String>): Int {
        val map = mapOf(
            "one" to 1, "two" to 2, "three" to 3, "four" to 4, "five" to 5, "six" to 6,
            "seven" to 7, "eight" to 8, "nine" to 9
        )
        return input.map { line ->
            line.mapIndexedNotNull { index, c ->
                if (c.isDigit()) c
                else line.possibleWordAt(index).firstNotNullOfOrNull { word -> map[word] }
            }.joinToString()
        }.sumOf { line -> "${line.first { i -> i.isDigit() }}${line.last { i -> i.isDigit() }}".toInt() }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(part2(testInput) == 358)

    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()

}

private fun String.possibleWordAt(index: Int): List<String> = (3 .. 5).map{ size -> substring(index, (index + size).coerceAtMost(length))}

