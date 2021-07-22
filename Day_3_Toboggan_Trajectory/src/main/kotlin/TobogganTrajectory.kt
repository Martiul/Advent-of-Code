import java.io.File

class TobogganTrajectory {

    fun tobogganTrajectory(grid: List<String>): Long {
        return countTrees(grid, 1, 3)
    }

    fun tobogganTrajectory2(grid: List<String>): Long {
        return countTrees(grid, 1, 1) *
            countTrees(grid, 1, 3) *
            countTrees(grid, 1, 5) *
            countTrees(grid, 1, 7) *
            countTrees(grid, 2, 1)
    }

    fun countTrees(grid: List<String>, dy: Int, dx: Int): Long {
        return grid
            .filterIndexed { idx, _ -> idx % dy == 0 }
            .filterIndexed { idx, line -> line[(idx*dx) % line.length] == '#'}
            .count()
            .toLong()
    }
}

fun main() {
    val input: List<String> = File("input.txt").readLines()
    val t = TobogganTrajectory()
    println(t.tobogganTrajectory(input))
    println(t.tobogganTrajectory2(input))
}
