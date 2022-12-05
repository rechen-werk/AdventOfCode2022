package december

import util.Input
import kotlin.collections.ArrayList

class December5 : December<String, String> {
    override fun star1(): String {
        val (stacks, instructions) = input()
        instructions.forEach {
            stacks[it["to"]]?.addAll(stacks[it["from"]]?.takeLast(it["move"]!!)?.reversed() ?: throw IllegalStateException())
            for (i in 0 until it["move"]!!) {
                stacks[it["from"]]?.removeLast()
            }
        }

        return stacks.map { it.value.last() }.joinToString("")
    }

    override fun star2(): String {
        val (stacks, instructions) = input()
        instructions.forEach {
            stacks[it["to"]]?.addAll(stacks[it["from"]]?.takeLast(it["move"]!!) ?: throw IllegalStateException())
            for (i in 0 until it["move"]!!) {
                stacks[it["from"]]?.removeLast()
            }
        }
        return stacks.map { it.value.last() }.joinToString("")
    }

    private fun input(): Pair<Map<Int, ArrayList<Char>>, List<Map<String, Int>>> = Input
        .get(5)
        .split("\n\n")
        .chunked(2) { it[0]
            .split("\n")
            .dropLast(1)
            .reversed()
            .map { it
                .filterIndexed { i, _ -> (i % 4) == 1 }
                .withIndex()
                .groupBy { it.index }
                .flatMap { it.value }
            }
            .flatten()
            .groupBy({ it.index }, { it.value })
            .map { it
                .value
                .dropLastWhile() { it.isWhitespace() } as ArrayList<Char>
            }.mapIndexed {i, v -> i+1 to v}
            .toMap() to it[1]
            .split("\n")
            .dropLast(1)
            .map { it
                .split(" ")
                .chunked(2) { it[0] to it[1].toInt() }
                .toMap()
            }
        }
        .first()
}