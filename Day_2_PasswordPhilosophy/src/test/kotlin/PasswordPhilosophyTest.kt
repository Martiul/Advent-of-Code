import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.arbitrary.list
import io.kotest.property.arbitrary.string
import io.kotest.property.arbitrary.stringPattern
import io.kotest.property.checkAll
import java.io.File
import java.lang.IllegalArgumentException

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
        context("Sample tests") {
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
        context("Empty passcode") {
            it("returns 0") {
                val actual = p.passwordPhilosophy(listOf("1-2 a: "))
                actual shouldBe 0
            }
        }
        context("Incorrect format") {
            it("throws IllegalArgumentException") {
                checkAll(Arb.list(Arb.string())) { s ->
                    shouldThrow<IllegalArgumentException> {
                        p.passwordPhilosophy(s)
                    }
                }
            }
        }
        context("Character count over limit") {
            it("Returns 0") {
                checkAll(Arb.list(Arb.stringPattern("1-[1-9] a: ([a]{10,20})"))) { s ->
                    p.passwordPhilosophy(s) shouldBe 0
                    p.passwordPhilosophy2(s) shouldBe 0
                }
            }
        }
        context("") {
            it("Returns 0") {
                checkAll(Arb.list(Arb.stringPattern("1-[1-9] a: ([a]{10,20})"))) { s ->
                    p.passwordPhilosophy(s) shouldBe 0
                }
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
        context("Sample tests") {
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
