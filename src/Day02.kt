import kotlin.math.max

fun main() {
    fun part1(input: List<String>): Int {
        val map = mapOf("red" to 12, "green" to 13, "blue" to 14)
        var result = 0
        for(line in input) {
            val game = line.substringBefore(":")
            val id = game.substringAfter("Game ")
            val isValid = line.substringAfter(":")
                .split(";")
                .stream()
                .map { it.trim() }
                .map { it.split(",") }
                .flatMap { it.stream() }
                .map { it.trim() }
                .map{it.split(" ")}
                .filter {it != null }
                .filter{ it.size == 2 }
                .filter{ map[it[1]]!! < it[0].toInt() }
                .findAny()
                .isEmpty
            if(isValid) {result+= id.toInt()}
        }
        return result
    }

    fun part2(input: List<String>): Int {
        var result = 0
        for(line in input) {
            val map = mutableMapOf("red" to 0, "green" to 0, "blue" to 0)
            line.substringAfter(":")
                .split(";")
                .stream()
                .map { it.trim() }
                .map { it.split(",") }
                .flatMap { it.stream() }
                .map { it.trim() }
                .map{it.split(" ")}
                .filter {it != null }
                .filter{ it.size == 2 }
                .forEach { map[it[1]] = max(map[it[1]]!!, it[0].toInt())}
            result += map.values.filter { it != 0 }.reduce(Int::times)
        }
        return result
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")
    part2(testInput).println()
    check(part2(testInput) == 2286)

    val input = readInput("Day02")
    part1(input).println()
    part2(input).println()
}
