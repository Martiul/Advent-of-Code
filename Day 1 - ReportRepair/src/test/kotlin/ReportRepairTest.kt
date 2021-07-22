import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import java.lang.IllegalArgumentException

class ReportRepairTest : DescribeSpec({
    val reportRepair = ReportRepair()

    describe("reportRepair") {
        context("Given no numbers") {
            it("throws exception") {
                shouldThrow<IllegalArgumentException> {
                    reportRepair.repair(listOf())
                }
            }
        }
        context("Answer is two of the same number") {
            it("returns product") {
                val actual = reportRepair.repair(listOf(1010, 1010))
                actual shouldBe 1020100
            }
        }
        context("Only one number") {
            it("throws exception") {
                shouldThrow<IllegalArgumentException> {
                    reportRepair.repair(listOf(1010))
                }
            }
        }
        context("Input has negative values") {
            it("returns product") {
                val actual = reportRepair.repair(listOf(-1, -2, -3, 2018, 2019, 2020, 2021))
                actual shouldBe -2021
            }
            it("returns product") {
                val actual = reportRepair.repair(listOf(2000, -2000, 20))
                actual shouldBe 40000
            }
        }
        context("Multiple answers") {
            it("Returns first") {
                val actual = reportRepair.repair(listOf(2020, 0, 1000, 1020, 2020, 2019, 1))
                actual shouldBe 0
            }
        }
    }

    describe("reportRepair2") {
        context("Empty list") {
            it("throw exception") {
                shouldThrow<IllegalArgumentException> {
                    reportRepair.repair2(listOf())
                }
            }
        }
        context("List of 0") {
            it("throw exception") {
                shouldThrow<IllegalArgumentException> {
                    reportRepair.repair2(listOf())
                }
            }
        }
        context("Zero is a factor") {
            it("returns 0") {
                val actual = reportRepair.repair2(listOf(1000, 1020, 2, 5, 0))
                actual shouldBe 0
            }
        }
        context("Multiple of the same entry") {
            it("returns product") {
                val actual = reportRepair.repair2(listOf(100, 200, 300, 2, 2, 2016, 3000))
                actual shouldBe 8064
            }
        }
    }
})
