import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import main.reportRepair
import main.reportRepair2

class mainTest: DescribeSpec({
    describe("reportRepair") {
//        context("When given no numbers") {
//            it("returns 0") {
//                val actual = reportRepair(listOf())
//                actual shouldBe 0
//            }
//        }
        context("Answer is two of the same number") {
            it("returns product") {
                val actual = reportRepair(listOf(1010,1010))
                actual shouldBe 1020100
            }
        }
        context("Input has negative values") {
            it("returns product") {
                val actual = reportRepair(listOf(-1, -2, -3, 2018, 2019, 2020, 2021))
                actual shouldBe -2021
            }
            it ("returns product") {
                val actual = reportRepair(listOf(2000, -2000, 20))
                actual shouldBe 40000
            }
        }
        context("Multiple answers") {
            it("Returns first") {
                val actual = reportRepair(listOf(2020, 0, 1000, 1020, 2020, 2019, 1))
                actual shouldBe 0
            }
        }
    }

    describe("reportRepair2") {
        context("Empty list") {
            it("returns 0") {
                val actual = reportRepair2(listOf())
                actual shouldBe 0
            }
        }
        context("List of 0") {
            it("returns 0") {
                val actual = reportRepair2(listOf(0,0))
                actual shouldBe 0
            }
        }
        context("Zero is a factor") {
            it("returns 0") {
                val actual = reportRepair2(listOf(1000, 1020, 2, 5, 0))
                actual shouldBe 0
            }
        }
        context("Multiple of the same entry") {
            it("returns product") {
                val actual = reportRepair2(listOf(100, 200, 300, 2, 2, 2016, 3000))
                actual shouldBe 8064
            }
        }
    }
})

