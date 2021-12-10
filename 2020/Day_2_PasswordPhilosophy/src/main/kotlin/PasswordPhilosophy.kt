import java.io.File

class PasswordPhilosophy {
    private val regex = """(\d+)-(\d+) (\w): (.*)""".toRegex()
    fun passwordPhilosophy(lines: List<String>): Int {
        require(lines.all { regex.matches(it) })
        return lines.filter { line ->
            regex.find(line)
                ?.let {
                    val (low, high, charToFind, password) = it.destructured
                    password.count { it == charToFind[0] } in low.toInt()..high.toInt()
                }
                ?: false
        }.count()
    }

    fun passwordPhilosophy2(lines: List<String>): Int {
        require(lines.all { regex.matches(it) })
        return lines.filter { line ->
            regex.find(line)
                ?.let {
                    val low = it.groupValues[1].toInt() - 1
                    val high = it.groupValues[2].toInt() - 1
                    val charToFind = it.groupValues[3][0]
                    val password = it.groupValues[4]

                    (high in low until password.length) &&
                        ((password[low] == charToFind) xor (password[high] == charToFind))
                }
                ?: false
        }.count()
    }
}

fun main() {
    val p = PasswordPhilosophy()
    val input: List<String> = File("input.txt").readLines()
    println(p.passwordPhilosophy(input))
    println(p.passwordPhilosophy2(input))
}
