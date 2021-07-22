import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import java.io.File

class PasswordPhilosophyTest : DescribeSpec({
    val p = PasswordPhilosophy()
    val inputFile: List<String> = File("input.txt").readLines()

    describe("passwordPhilosophy") {
        context("Input file") {
            val ans = 582
            it("returns $ans") {
                val actual = p.passwordPhilosophy(inputFile)
                actual shouldBe ans
            }
        }
        context("Basic tests") {
            it("returns 2") {
                val actual = p.passwordPhilosophy(
                    listOf(
                        "1-3 a: abcde",
                        "1-3 b: cdefg",
                        "2-9 c: ccccccccc"
                    )
                )
                actual shouldBe 2
            }
        }
        context("Empty string") {
            it("returns 0") {
                val actual = p.passwordPhilosophy(listOf("1-2 a: "))
                actual shouldBe 0
            }
        }
    }

    describe("passwordPhilosophy2") {
        context("Input file") {
            val ans = 729
            it("returns $ans") {
                val actual = p.passwordPhilosophy2(inputFile)
                actual shouldBe ans
            }
        }
        context("Basic tests") {
            it("returns 2") {
                val actual = p.passwordPhilosophy2(
                    listOf(
                        "1-3 a: abcde",
                        "1-3 b: cdefg",
                        "2-9 c: ccccccccc",
                        "1-3 c: abc",
                        "1-3 c: cbc"
                    )
                )
                actual shouldBe 2
            }
        }
    }
})
