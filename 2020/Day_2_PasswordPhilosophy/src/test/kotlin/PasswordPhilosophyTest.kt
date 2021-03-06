import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.ints.shouldBeLessThanOrEqual
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.arbitrary.*
import io.kotest.property.checkAll
import java.io.File
import java.lang.IllegalArgumentException

class PasswordPhilosophyTest : DescribeSpec({
    val p = PasswordPhilosophy()
    val inputFile: List<String> = File("input.txt").readLines()

    describe("passwordPhilosophy") {
        context("Sample test") {
            val ans = 582
            it("returns $ans") {
                val actual = p.passwordPhilosophy(inputFile)
                actual shouldBe ans
            }
        }
        context("One passport missing character") {
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
        context("Character count over limit") {
            it("Returns 0") {
                checkAll(Arb.list(Arb.stringPattern("1-[1-9] a: ([a]{10,20})"))) { s ->
                    p.passwordPhilosophy(s) shouldBe 0
                }
            }
        }
        context("All passports valid") {
            it("Returns size of input") {
                checkAll(Arb.list(Arb.stringPattern("[a-z]{0,100}"))) { lines ->
                    p.passwordPhilosophy(lines.map { code -> "1-${code.length} ${code[0]}: " + code }) shouldBe lines.size
                }
            }
        }
        context("Any number of passports") {
            it("Number of valid passports less than total number of passports") {
                checkAll(Arb.list(Arb.stringPattern("[1-9]-[1-9][1-9] [a-z]: ([a-z]{1,50})"))) { l ->
                    p.passwordPhilosophy(l) shouldBeLessThanOrEqual l.size
                }
            }
        }
        context("Incorrect format") {
            it("throws IllegalArgumentException") {
                checkAll(Arb.list(Arb.string()).filter { it.isNotEmpty() }) { s ->
                    shouldThrow<IllegalArgumentException> {
                        p.passwordPhilosophy(s)
                    }
                }
            }
        }
    }

    describe("passwordPhilosophy2") {
        context("Sample test") {
            val ans = 729
            it("returns $ans") {
                val actual = p.passwordPhilosophy2(inputFile)
                actual shouldBe ans
            }
        }
        context("Zero, one, and two occurrences of the character") {
            it("Returns 1 valid passports") {
                val actual = p.passwordPhilosophy2(
                    listOf(
                        "1-3 c: abc",
                        "1-3 c: cbc",
                        "1-3 c: aba"
                    )
                )
                actual shouldBe 1
            }
        }
        context("Occurrences at all indices except the specified") {
            it("Returns 0") {
                val actual = p.passwordPhilosophy2(
                    listOf(
                        "3-10 i: iiziiiiiiz",
                        "1-1 j: x",
                        "1-10 k: mkkkkkkkkm"
                    )
                )
                actual shouldBe 0
            }
        }
        context("Character count over limit") {
            it("Returns 0") {
                checkAll(Arb.list(Arb.stringPattern("1-[1-9] a: ([a]{10,20})"))) { s ->
                    p.passwordPhilosophy2(s) shouldBe 0
                }
            }
        }
        context("All passports valid") {
            it("Returns size of input") {
                checkAll(Arb.list(Arb.stringPattern("[a-z]{26}").map { it.toSet().fold("") { acc, element -> acc + element } })) { lines ->
                    p.passwordPhilosophy2(lines.map { code -> "1-${code.length} ${code[0]}: " + code }) shouldBe lines.size
                }
            }
        }
    }
})
