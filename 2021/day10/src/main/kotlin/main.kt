import java.io.File

fun getInput(fileName: String): List<String> {
    val f = File(fileName)
    val l = mutableListOf<String>()
    f.forEachLine { l.add(it) }
    return l
}

fun isMatchingBrace(leftBrace: Char, rightBrace: Char): Boolean {
    return when (leftBrace) {
        '(' -> rightBrace == ')'
        '[' -> rightBrace == ']'
        '{' -> rightBrace == '}'
        '<' -> rightBrace == '>'
        else -> false
    }
}

fun Char.isLeftBrace(): Boolean {
    return this in listOf('(', '[', '{', '<')
}

fun Char.isRightBrace(): Boolean {
    return this in listOf(')', ']', '}', '>')
}

fun rightBraceValue(c: Char): Int {
    return when (c) {
        ')' -> 3
        ']' -> 57
        '}' -> 1197
        '>' -> 25137
        else -> 0
    }
}

fun orphanBraceValue(c: Char): Int {
    return when (c) {
        '(' -> 1
        '[' -> 2
        '{' -> 3
        '<' -> 4
        else -> 0
    }
}

fun findSyntaxScore(line: String): Int {
    val stack: MutableList<Char> = mutableListOf()
    line.forEach {
        if (it.isLeftBrace()) {
            stack.add(it)
        } else if (stack.isEmpty() || !isMatchingBrace(stack.last(), it)) {
            return rightBraceValue(it)
        } else {
            stack.removeLast()
        }
    }
    return 0
}

fun findCompletionScore(line: String): Long {
    val stack: MutableList<Char> = mutableListOf()
    line.forEach {
        if (it.isLeftBrace()) {
            stack.add(it)
        } else if (stack.isEmpty() || !isMatchingBrace(stack.last(), it)) {
            return 0
        } else {
            stack.removeLast()
        }
    }
    return stack
        .foldRight(0) { orphanBrace, totalScore -> totalScore * 5 + orphanBraceValue(orphanBrace) }
}

fun partOne(fileName: String): Int {
    val l = getInput(fileName)
    return l.fold(0) { acc, line -> acc + findSyntaxScore(line) }
}

fun partTwo(fileName: String): Long {
    val l = getInput(fileName)
    val completionScores: List<Long> = l
        .map { findCompletionScore(it) }
        .filterNot { it == 0L }
        .sorted()
        .toList()
    println(completionScores)
    return completionScores[(completionScores.size - 1) / 2]
}

fun main(args: Array<String>) {
    println(partOne("sample.txt"))
    println(partOne("input.txt"))
    println(partTwo("sample.txt"))
    println(partTwo("input.txt"))
}
