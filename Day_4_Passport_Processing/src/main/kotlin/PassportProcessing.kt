
import java.io.File

enum class PassportFields(val value: String) {
    BYR("byr"),
    IYR("iyr"),
    EYR("eyr"),
    HGT("hgt"),
    HCL("hcl"),
    ECL("ecl"),
    PID("pid")
}

class PassportProcessing {
    fun passportProcessing(input: List<String>): Int {
        return transformToPassports(input).filter { it.isValid() }.count()
    }
    fun passportProcessing2(input: List<String>): Int {
        return transformToPassports(input).filter { it.isValid2() }.count()
    }
}

class Passport(private val data: Map<String, String>) {

    fun isValid(): Boolean {
        return PassportFields.values().all { data.contains(it.value) }
    }

    fun isValid2(): Boolean {
        return validBirthYear() &&
            validIssueYear() &&
            validExpiration() &&
            validHeight() &&
            validHairColor() &&
            validEyeColor() &&
            validPassportID()
    }

    private fun validBirthYear(): Boolean {
        val byr = data.getOrDefault(PassportFields.BYR.value, "")
        return (byr.toIntOrNull()?.let { it in (1920..2020) }) ?: false
    }

    private fun validIssueYear(): Boolean {
        val iyr = data.getOrDefault(PassportFields.IYR.value, "")
        return (iyr.toIntOrNull()?.let { it in (2010..2020) }) ?: false
    }

    private fun validExpiration(): Boolean {
        val eyr = data.getOrDefault(PassportFields.EYR.value, "")
        return (eyr.toIntOrNull()?.let { it in (2020..2030) }) ?: false
    }

    private fun validHeight(): Boolean {
        val hgt = data.getOrDefault(PassportFields.HGT.value, "")
        val num = if (hgt.length >= 2) hgt.take(hgt.length - 2).toInt() else 0
        val dimension = if (hgt.length >= 2) hgt.takeLast(2) else ""
        return (dimension == "cm" && num in 150..193) || (dimension == "in" && num in 59..76)
    }

    private fun validHairColor(): Boolean {
        val hcl = data.getOrDefault(PassportFields.HCL.value, "")
        return hcl.length == 7 && hcl[0] == '#' && (hcl.drop(1).all { Character.isDigit(it) || it in ('a'..'f') })
    }

    private fun validEyeColor(): Boolean {
        return data.getOrDefault(PassportFields.ECL.value, "") in listOf("amb", "blu", "brn", "gry", "grn", "hzl", "oth")
    }

    private fun validPassportID(): Boolean {
        return data.getOrDefault(PassportFields.PID.value, "").matches("""\d{9}""".toRegex())
    }
}

// Determine Passport info which is separated by empty lines
fun transformToPassports(data: List<String>): List<Passport> {
    return (data + "").fold(listOf<Passport>() to mapOf<String, String>()) { (passports, passportInfo), line ->
        if (line == "") {
            ((passports + Passport(passportInfo)) to mapOf())
        } else {
            passports to (passportInfo + keyValuesToMap(line))
        }
    }.first
}

fun keyValuesToMap(line: String): Map<String, String> {
    val allKeyValuesRegex = """([^:]*[^ ]*)""".toRegex()
    val keyValueRegex = """([^ ]*):([^ ]*)$""".toRegex()

    return allKeyValuesRegex.findAll(line)
        .toList()
        .fold(mapOf()) { acc, match ->
            if (match.value != "") {
                val (key, value) = keyValueRegex.find(match.value)!!.let { it.destructured }
                (acc + Pair(key, value))
            } else {
                acc
            }
        }
}

fun main() {
    val input: List<String> = File("input.txt").readLines()
    val x = PassportProcessing()
    val basic: List<String> = listOf(
        "ecl:gry pid:860033327 eyr:2020 hcl:#fffffd",
        "byr:1937 iyr:2017 cid:147 hgt:183cm",
        "",
        "iyr:2013 ecl:amb cid:350 eyr:2023 pid:028048884",
        "hcl:#cfa07d byr:1929",
        "",
        "hcl:#ae17e1 iyr:2013",
        "eyr:2024",
        "ecl:brn pid:760753108 byr:1931",
        "hgt:179cm",
        "",
        "hcl:#cfa07d eyr:2025 pid:166559648",
        "iyr:2011 ecl:brn hgt:59"
    )

    println(x.passportProcessing(basic))
    println(x.passportProcessing(input))
    println(x.passportProcessing2(input))
}
