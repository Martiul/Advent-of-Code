import java.io.File

fun getInput(fileName: String): MutableList<MutableList<Int>> {
    val f = File(fileName)
    val l = mutableListOf<MutableList<Int>>()
    f.forEachLine { line -> l.add(line.map { it.digitToInt() }.toMutableList()) }
    return l
}

fun List<List<*>>.print() {
    for (i in this) {
        println(i)
    }
    println()
}

fun increment(grid: MutableList<MutableList<Int>>, flashed: Array<Array<Boolean>>, y: Int, x: Int): Int {
    if (y in (0 until grid.size) && x in (0 until grid[0].size) && !flashed[y][x]) {
        grid[y][x] += 1
        if (grid[y][x] >= 10) {
            flashed[y][x] = true
            grid[y][x] = 0
            var flashes = 1
            for (dy in -1..1) {
                for (dx in -1..1) {
                    if (dy != 0 || dx != 0) {
                        flashes += increment(grid, flashed, y + dy, x + dx)
                    }
                }
            }
            return flashes
        }
    }
    return 0
}

fun step(grid: MutableList<MutableList<Int>>): Int {
    val flashed: Array<Array<Boolean>> = Array(grid.size) { Array(grid[0].size) { false } }
    var flashes = 0
    for (y in 0 until grid.size) {
        for (x in 0 until grid[0].size) {
            flashes += increment(grid, flashed, y, x)
        }
    }
    return flashes
}

fun partOne(fileName: String): Int {
    val grid = getInput(fileName).toMutableList()
    var sum: Int = 0
    repeat(100) {
        sum += step(grid)
    }
    return sum
}

fun partTwo(fileName: String): Int {
    val grid = getInput(fileName).toMutableList()
    repeat(1000) {
        step(grid)
        if (grid.fold(0) { acc, mutableList -> acc + mutableList.sum() } == 0) return it + 1
    }
    return 0
}

fun main(args: Array<String>) {
//    print(partOne("tiny.txt"))
    println(partOne("sample.txt"))
    println(partOne("input.txt"))
    println(partTwo("sample.txt"))
    println(partTwo("input.txt"))
}
