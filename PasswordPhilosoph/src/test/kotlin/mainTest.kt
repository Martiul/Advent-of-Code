import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe


class mainTest: DescribeSpec({
    describe("passwordPhilopshy2") {
        context("Basic tests") {
            it("returns 1") {
                val actual = passwordPhilosophy2(
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
