import java.io.File

fun getInput(fileName: String) = File("src/main/resources/input/$fileName.txt").readLines()

fun main() {
    println("part_1: ${part1()}")
    println("part_2: ${part2()}")
}

private fun part1() = getInput("aoc_1")
        .map { it.replace(Regex("[A-z]"), "") }
        .sumOf { "${it[0]}${it[it.length - 1]}".toInt() }

private fun part2() = getInput("aoc_1")
        .map { findFirstDigit(it.toCharArray()) + findLastDigit(it.toCharArray()) }
        .sumOf(String::toInt)

private fun findFirstDigit(input: CharArray): String {
    var tracker = ""

    for (char in input) {
        if (char.isDigit()) return char.toString()

        tracker += char
        val numberFromString = getNumberFromString(tracker)
        if (numberFromString.isNotEmpty()) return getNumberFromString(tracker)
    }

    throw Exception("No digit found")
}

private fun findLastDigit(input: CharArray): String {
    var tracker = ""

    for (char in input.reversed()) {
        if (char.isDigit()) return char.toString()

        tracker = "$char$tracker"
        val numberFromString = getNumberFromString(tracker)
        if (numberFromString.isNotEmpty()) return getNumberFromString(tracker)
    }

    throw Exception("No digit found")
}

private fun getNumberFromString(input: String): String {
    val match = wordsToNumbers.keys.filter { it in input }
    return if (match.isNotEmpty()) wordsToNumbers[match.first()]!! else ""
}

private val wordsToNumbers = mapOf(
    "one" to "1",
    "two" to "2",
    "three" to "3",
    "four" to "4",
    "five" to "5",
    "six" to "6",
    "seven" to "7",
    "eight" to "8",
    "nine" to "9",
)