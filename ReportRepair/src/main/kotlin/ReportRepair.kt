import java.io.File
import java.lang.IllegalArgumentException

class ReportRepair {
    val YEAR: Int = 2020

    // Assumptions
    //  There will always be at least one pair of entries summing to 2020 (otherwise exception)
    //  If multiple, return the first found going left-to-right
    //  Negative and zero are allowed
    fun repair(numbers: List<Int>): Int {
        val p = twoSum(numbers, YEAR)
        if (p != null) {
            return p.first * p.second
        }
        throw IllegalArgumentException("List does not have pair that sums to $YEAR")
    }

    fun repair2(numbers: List<Int>): Int {
        val t = threeSum(numbers, YEAR)
        if (t != null) {
            return t[0] * t[1] * t[2]
        }
        throw IllegalArgumentException("List does not have triple that sums to $YEAR")
    }

    // Given a list of numbers, returns two numbers in the list that sum to target
    // Returns null if no matching pair
    private fun twoSum(numbers: List<Int>, target: Int): Pair<Int, Int>? {
        val (ans, _) = numbers.fold((null as Int? to mutableSetOf<Int>())){ (ans, seen), element ->
            when {
                ans != null -> (ans to seen)
                seen.contains(target-element) -> (element to seen)
                else -> {
                    seen.add(element)
                    (ans to seen)
                }
            }
        }
        if (ans != null) {
            return ans to (target-ans)
        }
        return null
    }

    private fun threeSum(numbers: List<Int>, target: Int) : List<Int>?{
        val ans = numbers.foldIndexed(null as List<Int>?){idx, ans, element ->
            val twoSumAns = twoSum(numbers.drop(idx+1), target-numbers[idx])
            when {
                ans != null || twoSumAns == null -> ans
                else -> listOf(numbers[idx], twoSumAns.first, twoSumAns.second)
            }
        }
        return ans ?: null
    }
}

fun main() {
    val input: List<String> = File("input.txt").readLines()
    val reportRepair = ReportRepair()
    println(reportRepair.repair(input.map{it.toInt()}))
    println(reportRepair.repair2(input.map{it.toInt()}))
}
