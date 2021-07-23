import java.io.File

class CustomCustoms {
    fun customCustoms(input: List<String>): Int {
        return parseGroups(input).map { it.countUniqueChars() }.sum()
    }
    fun customCustoms2(input: List<String>): Int {
        return parseGroups(input).map { it.countCommonChars() }.sum()
    }

    private fun parseGroups(input: List<String>): List<Group> {
        return (input + "").fold(listOf<Group>() to listOf<String>()) { (groups, groupInfo), line ->
            when (line) {
                "" -> (groups + Group(groupInfo)) to listOf()
                else -> groups to (groupInfo + line)
            }
        }.first
    }
}

class Group(private val lines: List<String>) {
    fun countUniqueChars(): Int {
        return lines.toString().filter { Character.isLetter(it) }.toSet().size
    }

    fun countCommonChars(): Int {
        val asString = lines.toString().filter { Character.isLetter(it) }
        return ('a'..'z').filter { ch -> asString.count { it == ch } == lines.size }.size
    }
}

fun main() {
    val input: List<String> = File("input.txt").readLines()
    val basic = listOf(
        "abc",
        "",
        "a",
        "b",
        "c",
        "",
        "ab",
        "ac",
        "",
        "a",
        "a",
        "a",
        "a",
        "",
        "b",
    )

    val c = CustomCustoms()
    println(c.customCustoms(basic))
    println(c.customCustoms2(basic))
    println(c.customCustoms(input))
    println(c.customCustoms2(input))
}
