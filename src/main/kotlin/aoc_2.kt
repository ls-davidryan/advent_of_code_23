fun main() {
    println("part_1: ${part1()}")
    println("part_2: ${part2()}")
}

private fun part1() = getInput("aoc_2")
    .filter { isGamePossible(getMinColourCountsPerGame(it)) }
    .sumOf { getGameId(it) }

private fun part2() = getInput("aoc_2")
    .sumOf { getMinColourCountsPerGame(it).values.reduce(Int::times) }

private fun getGameId(input: String) = input.substring(input.indexOf(" ") + 1, input.indexOf(":")).toInt()

private fun getSamples(input: String) = input.substring(input.indexOf(":") + 2).replace("; ", ";").split(";")

private fun getColourCounts(input: String) = input.replace(", ", ",").split(",")

private fun getMinColourCountsPerGame(input: String): Map<String, Int> {
    val knownMinPerColour = mutableMapOf(
        "red" to 0,
        "blue" to 0,
        "green" to 0
    )

    getSamples(input).forEach { sample ->
        getColourCounts(sample).forEach { colourCount ->
            val (count, colour) = colourCount.split(" ")
            if (count.toInt() > knownMinPerColour[colour]!!) {
                knownMinPerColour[colour] = count.toInt()
            }
        }
    }

    return knownMinPerColour

}

private fun isGamePossible(game: Map<String, Int>) =
    game["red"]!! <= 12 && game["green"]!! <= 13 && game["blue"]!! <= 14