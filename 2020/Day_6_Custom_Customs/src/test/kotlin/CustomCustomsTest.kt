import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.arbitrary.list
import io.kotest.property.arbitrary.stringPattern
import io.kotest.property.checkAll
import java.io.File

class CustomCustomsTest : DescribeSpec({
    val c = CustomCustoms()
    val input = File("input.txt").readLines()

    describe("Test customCustoms") {
        context("Sample test") {
            val expected = 6565
            it("Returns $expected") {
                val actual = c.customCustoms(input)
                actual shouldBe expected
            }
        }
        context("All groups answered one question") {
            checkAll(Arb.list(Arb.stringPattern("[a-z]"))) { letters ->
                // Groups separated by newline
                val withNewLine = letters.fold(listOf<String>()) { acc, element -> acc + element + "" }
                c.customCustoms(withNewLine.dropLast(1)) shouldBe letters.size
            }
        }
    }

    describe("Test customCustoms2") {
        context("Sample test") {
            val expected = 3137
            it("Returns $expected") {
                val actual = c.customCustoms2(input)
                actual shouldBe expected
            }
        }
    }
})
