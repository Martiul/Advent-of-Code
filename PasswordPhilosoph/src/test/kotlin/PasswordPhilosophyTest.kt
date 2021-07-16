import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe


class PasswordPhilosophyTest: DescribeSpec({
    val p = PasswordPhilosophy()

    describe("passwordPhilosophy") {
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
    }

    describe("passwordPhilosophy2") {
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
