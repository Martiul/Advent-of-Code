import java.io.File

class BinaryBoarding {
    companion object {
        const val R_LOW = 0
        const val R_HIGH = 127
        const val C_LOW = 0
        const val C_HIGH = 7
    }

    // Returns the maximum seat ID of the codes in the input
    fun binaryBoarding(input: List<String>): Int {
        validateInput(input)
        return input.fold(0) { max, line -> maxOf(max, getSeatID(line)) }
    }
    fun binaryBoarding2(input: List<String>): Int {
        validateInput(input)
        val greatestID = binaryBoarding(input)
        val notSeen = input.map { getSeatID(it) }.fold((8..greatestID).toSet()) { notSeen, seatID -> notSeen - seatID }
        return notSeen.drop(1).first { !(notSeen.contains(it-1) || notSeen.contains(it+1)) }
    }

    private fun validateInput(input: List<String>) {
        require(
            input.isNotEmpty() &&
                input.all {
                    it.length == 10 &&
                        it.take(7).all { it == 'B' || it == 'F' } &&
                        it.takeLast(3).all { it == 'R' || it == 'L' }
                }
        )
    }

    internal fun getSeatID(code: String): Int {
        val row = code.take(7)
            .fold(R_LOW to R_HIGH) { p, next -> binarySearchStep(p, next == 'F') }
            .first
        val col = code.takeLast(3)
            .fold(C_LOW to C_HIGH) { p, next -> binarySearchStep(p, next == 'L') }
            .first
        return 8 * row + col
    }

    private fun binarySearchStep(p: Pair<Int, Int>, lower: Boolean): Pair<Int, Int> {
        // fun(low, high)
        // F: fun(low, floor(mid))
        // B: fun(ceil(mid), high)
        val (low, high) = p
        val mid = low + (high - low) / 2.0
        if (lower) {
            return (low to kotlin.math.floor(mid).toInt())
        }
        return (kotlin.math.ceil(mid).toInt() to high)
    }
}

fun main() {
    val b = BinaryBoarding()
    val input = File("input.txt").readLines()
    println(b.binaryBoarding(input))
    println(b.binaryBoarding2(input))
}
