import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.arbitrary.*
import io.kotest.property.checkAll
import java.io.File

class PassportProcessingTest : DescribeSpec({
    val p = PassportProcessing()
    val input: List<String> = File("input.txt").readLines()

    describe("Test PassportProcessing") {
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
            it("Return size of input") {
                checkAll(Arb.list(passportStringGen)) { passports ->
                    // Add newlines to separate passports, and remove last newline
                    val withNewLine = passports
                        .fold(listOf<String>()) { acc, element -> acc + element + "" }
                        .dropLast(1)
                    p.passportProcessing(withNewLine) shouldBe passports.size
                }
            }
        }
    }
    describe("Test PassportProcessing2") {
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
        context("All valid passports") {
            it("Return size of input") {
                checkAll(Arb.list(passportStringGen)) { passports ->
                    // Add newlines to separate passports, and remove last newline
                    val withNewLine = passports
                        .fold(listOf<String>()) { acc, element -> acc + element + "" }
                        .dropLast(1)
                    p.passportProcessing2(withNewLine) shouldBe passports.size
                }
            }
        }

        context("Valid Passport (control)") {
            it("Valid passport") {
                val actual = p.passportProcessing2(listOf(Passport(getValidPassportData()).toString()))
                actual shouldBe 1
            }
        }

        context("Passport BYR out of range") {
            it("Invalid passport") {
                val data = getValidPassportData(byr = "0")
                val actual = p.passportProcessing2(listOf(Passport(data).toString()))
                actual shouldBe 0
            }
        }
        context("Passport IYR out of range") {
            it("Invalid passport") {
                val data = getValidPassportData(byr = "2021")
                val actual = p.passportProcessing2(listOf(Passport(data).toString()))
                actual shouldBe 0
            }
        }
        context("Passport EYR out of range") {
            it("Invalid passport") {
                val data = getValidPassportData(eyr = "2031")
                val actual = p.passportProcessing2(listOf(Passport(data).toString()))
                actual shouldBe 0
            }
        }
        context("Passport HGT out of range") {
            it("Invalid passport") {
                val data = getValidPassportData(hgt = "200cm")
                val actual = p.passportProcessing2(listOf(Passport(data).toString()))
                actual shouldBe 0
            }
        }
        context("Passport HCL out of range") {
            it("Invalid passport") {
                val data = getValidPassportData(hcl = "###0")
                val actual = p.passportProcessing2(listOf(Passport(data).toString()))
                actual shouldBe 0
            }
        }
        context("Passport ECL out of range") {
            it("Invalid passport") {
                val data = getValidPassportData(ecl = "not color")
                val actual = p.passportProcessing2(listOf(Passport(data).toString()))
                actual shouldBe 0
            }
        }
        context("Passport PID out of range") {
            it("Invalid passport") {
                val data = getValidPassportData(pid = "0")
                val actual = p.passportProcessing2(listOf(Passport(data).toString()))
                actual shouldBe 0
            }
        }
    }
})

fun getValidPassportData(
    byr: String = "2000",
    iyr: String = "2010",
    eyr: String = "2020",
    hgt: String = "150cm",
    hcl: String = "#ffffff",
    ecl: String = "amb",
    pid: String = "123456789"
): MutableMap<String, String> = mutableMapOf(
    PassportFields.BYR.value to byr,
    PassportFields.IYR.value to iyr,
    PassportFields.EYR.value to eyr,
    PassportFields.HGT.value to hgt,
    PassportFields.HCL.value to hcl,
    PassportFields.ECL.value to ecl,
    PassportFields.PID.value to pid
)

val passportStringGen = Arb.bind(
    Arb.int(1920, 2002),
    Arb.int(2010, 2020),
    Arb.int(2020, 2030),
    Arb.int(150, 193),
    Arb.stringPattern("\\#([0-9]|[a-f]){6}"),
    Arb.stringPattern("amb|blu|brn|gry|grn|hzl|oth"),
    Arb.stringPattern("[0-9]{9,9}")
) { byr, iyr, eyr, hgt, hcl, ecl, pid ->
    "byr:$byr iyr:$iyr eyr:$eyr hgt:${hgt}cm hcl:$hcl ecl:$ecl pid:$pid"
}
