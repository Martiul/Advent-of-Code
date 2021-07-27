import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.ints.shouldBeGreaterThan
import io.kotest.matchers.ints.shouldBeInRange
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.arbitrary.*
import io.kotest.property.checkAll
import java.io.File
import java.lang.IllegalArgumentException

class BinaryBoardingTest : DescribeSpec({
    val b = BinaryBoarding()
    val input = File("input.txt").readLines()

    describe("Test binaryBoarding") {
        context("Sample test") {
            val expected = 926
            it("Returns $expected") {
                b.binaryBoarding(input) shouldBe expected
            }
        }
        context("Bounds of seat ID") {
            checkAll(Arb.list(Arb.stringPattern("[BF]{7}[RL]{3}"), 1..100)) { l ->
                b.binaryBoarding(l) shouldBeInRange 0..(8 * BinaryBoarding.R_HIGH + BinaryBoarding.C_HIGH)
            }
        }
        context("Leading B yields larger seat ID") {
            checkAll(
                Arb.bind(
                    Arb.list(Arb.stringPattern("B[BF]{6}[LR]{3}"), 1..100),
                    Arb.list(Arb.stringPattern("F[BF]{6}[LR]{3}"), 1..100)
                ) { low, high -> Pair(low, high) }
            ) { p ->
                b.binaryBoarding(p.first) shouldBeGreaterThan b.binaryBoarding(p.second)
            }
        }
        context("Invalid input") {
            checkAll(Arb.list(Arb.string()).filter { it.size != 10 }) { l ->
                shouldThrow<IllegalArgumentException> {
                    b.binaryBoarding(l)
                }
            }
        }
    }

    describe("Test binaryBoarding2") {
        context("Sample test") {
            val expected = 657
            it("Returns $expected") {
                val actual = b.binaryBoarding2(input)
                actual shouldBe expected
            }
        }
        context("Only one seat taken") {
            it("Returns NoSuchElementException") {
                shouldThrow<NoSuchElementException> {
                    // Was guaranteed -1 and +1 are in the input
                    b.binaryBoarding2(listOf("BBBBBBBRRR"))
                }
            }
        }
    }

    describe("Test getSeatID helper") {
        context("Sample code to Seat ID mapping") {
            listOf(
                "BFFFBBFRRR" to 567,
                "FFFBBBFRRR" to 119,
                "BBFFBBFRLL" to 820
            ).forAll { (code, seatID) ->
                b.getSeatID(code) shouldBe seatID
            }
        }
    }
})
