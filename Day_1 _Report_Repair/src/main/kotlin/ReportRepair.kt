import java.io.File
import java.lang.IllegalArgumentException

class ReportRepair {
    val YEAR: Int = 2020

    // Assumptions
    //  There will always be at least one pair of entries summing to 2020 (otherwise exception)
    //  If multiple, return the first found going left-to-right
    //  Negative and zero are allowed
    fun repair(numbers: List<Int>): Int {
        return twoSum(numbers, YEAR)
            ?.let { (a, b) -> a * b }
            ?: throw IllegalArgumentException("List does not have pair that sums to $YEAR")
    }

    fun repair2(numbers: List<Int>): Int {
        return threeSum(numbers, YEAR)
            ?.let { (a, b, c) -> a * b * c }
            ?: throw IllegalArgumentException("List does not have pair that sums to $YEAR")
    }

    // Given a list of numbers, returns two numbers in the list that sum to target
    // Returns null if no matching pair
    private fun twoSum(numbers: List<Int>, target: Int): Pair<Int, Int>? {
        return numbers
            .fold((null as Int? to setOf<Int>())) { (ans, seen), element ->
                when {
                    ans != null -> (ans to seen)
                    seen.contains(target - element) -> (element to seen)
                    else -> ans to (seen + element)
                }
            }
            .first
            ?.let { it to (target - it) }
    }

    private fun threeSum(numbers: List<Int>, target: Int): List<Int>? {
        return numbers.foldIndexed(null as List<Int>?) { idx, ans, element ->
            val twoSumAns = twoSum(numbers.drop(idx + 1), target - numbers[idx])
            when {
                ans != null || twoSumAns == null -> ans
                else -> listOf(numbers[idx], twoSumAns.first, twoSumAns.second)
            }
        }
    }
}

fun main() {
    val input: List<String> = File("input.txt").readLines()
    val reportRepair = ReportRepair()
    println(reportRepair.repair(input.map { it.toInt() }))
    println(reportRepair.repair2(input.map { it.toInt() }))
}
