import java.io.File

class PasswordPhilosophy {
    fun passwordPhilosophy(lines: List<String>): Int {
        // 3-10 j: jjpjjrjjjjjjw
        val regex = """(\d+)-(\d+) (\w): (.*)""".toRegex()
        return lines.filter{line ->
            val result = regex.find(line)
            if (result != null) {
                val low = result.groupValues[1].toInt()
                val high = result.groupValues[2].toInt()
                val charToFind = result.groupValues[3]
                val password = result.groupValues[4]
                val count = password.count{it == charToFind.get(0)}
                count in low..high
            } else {
                false
            }
        }.count()
    }

    fun passwordPhilosophy2(lines: List<String>): Int {
        val regex = """(\d+)-(\d+) (\w): (.*)""".toRegex()
        return lines.filter{line ->
            val result = regex.find(line)
            if (result != null) {
                val low = result.groupValues[1].toInt()
                val high = result.groupValues[2].toInt()
                val charToFind = result.groupValues[3].get(0)
                val password = result.groupValues[4]
                (high in low until (password.length)+1) && (password[low-1] != password[high-1]) && (password[low-1] == charToFind || password[high-1] == charToFind)
            } else {
                false
            }
        }.count()
    }
}

fun main() {
    val p = PasswordPhilosophy()
    val input: List<String> = File("input.txt").readLines()
    println(p.passwordPhilosophy(input))
    println(p.passwordPhilosophy2(input))
}


