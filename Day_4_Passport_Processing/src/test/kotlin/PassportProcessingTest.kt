import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.arbitrary.filter
import io.kotest.property.arbitrary.list
import io.kotest.property.arbitrary.of
import io.kotest.property.arbitrary.string
import io.kotest.property.checkAll
import java.io.File

class PassportProcessingTest : DescribeSpec({
    val p = PassportProcessing()
    val input: List<String> = File("input.txt").readLines()

    // Properties: ans < number of empty lines
    describe("PassportProcessing") {
        context("Sample test") {
            it("Returns 213") {
                val actual = p.passportProcessing(input)
                actual shouldBe 213
            }
        }
        context("No passports") {
            it("Returns 0") {
                val actual = p.passportProcessing(listOf())
                actual shouldBe 0
            }
        }
        context("All valid passports") {
            it("Returns number of passports") {

//                checkAll(Arb.list(
//                    Arb.of("byr:" + Arb.string() +
//                        "iyr:" + Arb.string()
//                    ))) { data ->
//                    p.passportProcessing(data) shouldBe 0
//                }
                checkAll(
                    Arb.list(
                        Arb.of(
                            // Valid passport info string
                            PassportFields.values().fold("") { acc, element -> acc + element.value + ":" + Arb.string().filter { it.all { it != ' ' && it != ':' && it != '$'}} + " " }
                        )
                    )
                ) { data ->
                    val splitPassports = data.fold(listOf<String>()) { acc, element -> acc + element.dropLast(1) + "" }
                    p.passportProcessing(splitPassports.dropLast(1)) shouldBe data.size
                }
            }
        }
    }
    describe("PassportProcessing2") {
        context("Sample test") {
            it("Returns 147") {
                val actual = p.passportProcessing2(input)
                actual shouldBe 147
            }
        }
        context("No passports") {
            it("Returns 0") {
                val actual = p.passportProcessing(listOf())
                actual shouldBe 0
            }
        }
    }
})

// fun passportDataGenerator() : List<Arb<String>> {
//    return PassportFields.values().map{it.toString() to Arb.string()}.toMap()
// }
