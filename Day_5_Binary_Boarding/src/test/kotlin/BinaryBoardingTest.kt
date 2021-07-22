import io.kotest.core.spec.style.DescribeSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe

class BinaryBoardingTest : DescribeSpec({
    val b = BinaryBoarding()
    describe("Test Seat ID") {
        listOf(
            "BFFFBBFRRR" to 567,
            "FFFBBBFRRR" to 119,
            "BBFFBBFRLL" to 820
        ).forAll { (code, seatID) ->
            b.getSeatID(code) shouldBe seatID
        }
    }
})
