import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import java.io.File

class TobogganTrajectoryTest : DescribeSpec({
    val t = TobogganTrajectory()
    describe("Toboggan Trajectory") {
        context("Basic test") {
            val input: List<String> = File("input.txt").readLines()
            val actual = t.tobogganTrajectory(input)
            actual shouldBe 207
        }
    }
    describe("Toboggan Trajectory 2") {
        context("Basic test") {
            val input: List<String> = File("input.txt").readLines()
            val actual = t.tobogganTrajectory(input)
            actual shouldBe 2655892800
        }
    }
})
