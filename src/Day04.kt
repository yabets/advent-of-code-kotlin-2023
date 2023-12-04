fun main() {
    fun part1(input: List<String>): Int {
        return input.stream()
            .map { it.split(":").last() }
            .map { it.split("|") }
            .map { card ->
                Pair(
                card.first().trim().split("\\s+".toRegex()).stream().map(String::toInt).toList(),
                card.last().trim().split("\\s+".toRegex()).stream().map(String::toInt).toList()
                )}
            .map { (winningNumber, myNumber) -> winningNumber!!.intersect(myNumber!!).count() }
            .map { Math.pow(2.0, it.toDouble() - 1).toInt() }

            .reduce(0, Int::plus)
    }

    fun part2(input: List<String>): Int {
        var result = 0
        val numOfCards = IntArray(input.size) { 1 }
        var i = 0
        while(i < input.size) {
            val card = input[i].split(":").last()
            val winningNumber = card.split("|").first().trim().split("\\s+".toRegex()).stream().map(String::toInt).toList()
            val myNumber = card.split("|").last().trim().split("\\s+".toRegex()).stream().map(String::toInt).toList()
            val matches = winningNumber.intersect(myNumber).count()
            for(j in i + 1 until i + matches + 1) {
                numOfCards[j] += numOfCards[i]
            }
            result += numOfCards[i]
            i++
        }

        return result
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day04_test")
    check(part2(testInput) == 30)

    val input = readInput("Day04")
    part1(input).println()
    part2(input).println()

}

