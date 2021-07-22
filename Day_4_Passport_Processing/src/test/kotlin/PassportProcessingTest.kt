import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import java.io.File

class PassportProcessingTest : DescribeSpec({
    val p = PassportProcessing()
    val input: List<String> = File("input.txt").readLines()

    describe("PassportProcessing") {
        context("Basic test") {
            it("Returns 213") {
                val actual = p.passportProcessing(input)
                actual shouldBe 213
            }
        }
    }
    describe("PassportProcessing2") {
        context("Basic test") {
            it("Returns 147") {
                val actual = p.passportProcessing2(input)
                actual shouldBe 147
            }
        }
    }
})
