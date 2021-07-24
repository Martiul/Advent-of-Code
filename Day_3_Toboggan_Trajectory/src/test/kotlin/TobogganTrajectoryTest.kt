import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.arbitrary.map
import io.kotest.property.arbitrary.positiveInts
import io.kotest.property.checkAll
import java.io.File

class TobogganTrajectoryTest : DescribeSpec({
    val t = TobogganTrajectory()
    describe("Toboggan Trajectory") {
        context("Sample input") {
            val input: List<String> = File("input.txt").readLines()
            val actual = t.tobogganTrajectory(input)
            actual shouldBe 207
        }

        context("Grid of no trees") {
            it("Returns 0") {
                checkAll(Arb.positiveInts(100).map { (".".repeat(it) + "\n").repeat(it) }) { g ->
                    t.tobogganTrajectory(g.split("\n").dropLast(1)) shouldBe 0
                }
            }
        }
        context("Grid of only trees") {
            it("Returns height of grid") {
                checkAll(Arb.positiveInts(100).map { ("#".repeat(it) + "\n").repeat(it) }) { g ->
                    val grid = g.split("\n").dropLast(1)
                    t.tobogganTrajectory(grid) shouldBe grid.size
                }
            }
        }
    }
    describe("Toboggan Trajectory 2") {
        context("Sample test") {
            val input: List<String> = File("input.txt").readLines()
            val actual = t.tobogganTrajectory(input)
            actual shouldBe 2655892800
        }
        context("Grid of no trees") {
            it("Returns 0") {
                checkAll(Arb.positiveInts(100).map { (".".repeat(it) + "\n").repeat(it) }) { g ->
                    t.tobogganTrajectory2(g.split("\n").dropLast(1)) shouldBe 0
                }
            }
        }
        context("Grid of only trees") {
            it("Returns height of grid") {
                checkAll(Arb.positiveInts(100).map { ("#".repeat(it) + "\n").repeat(it) }) { g ->
                    val grid = g.split("\n").dropLast(1)
                    t.tobogganTrajectory2(grid) shouldBe (Math.pow(grid.size.toDouble(), 4.0) * Math.ceil(grid.size / 2.0)).toLong()
                }
            }
        }
    }
})
