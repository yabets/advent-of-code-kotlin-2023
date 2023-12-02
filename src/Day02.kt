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
        return input.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")
    part1(testInput).println()
    check(part1(testInput) == 8)

    val input = readInput("Day02")
    part1(input).println()
    part2(input).println()
}
