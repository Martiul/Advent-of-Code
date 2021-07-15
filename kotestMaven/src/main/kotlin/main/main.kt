package main

import java.io.File

fun main(args: Array<String>) {
    val input: List<String> = File("input.txt").readLines()
    println(reportRepair(input.map{it.toInt()}))
    println(reportRepair2(input.map{it.toInt()}))
}

// 2 sum
fun reportRepair(numbers: List<Int>): Int {
    val year = 2020
    var seen = mutableSetOf<Int>()
    seen.addAll(numbers)
    return seen.first{seen.contains(year-it)}.let {
        it*(year-it)
    }
}

fun reportRepair2(numbers: List<Int>): Int {
    val year = 2020

    val sorted = numbers.sorted()

    // Pivot + two pointer
    for (i in 0 until sorted.size-2) {
        val valCur = sorted[i]
        var left = i+1
        var right = sorted.size-1
        while (left < right) {
            val valLeft = sorted[left]
            val valRight = sorted[right]
            if (valCur+valLeft+valRight == year) {
                return valCur*valLeft*valRight
            }
            if (year-valCur-valLeft-valRight < 0) {
                right--
            } else {
                left++
            }
        }
    }
    return 0
}
