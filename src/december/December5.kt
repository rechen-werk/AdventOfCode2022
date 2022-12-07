package december

import util.Input
import kotlin.collections.ArrayList

class December5 : December<String, String> {
    override fun star1(): String {
        val (stacks, instructions) = input()
        instructions.forEach {
            stacks[it[2]-1].addAll(stacks[it[1]-1].takeLast(it[0]).reversed())
            for (i in 0 until it[0]) {
                stacks[it[1]-1].removeLast()
            }
        }

        return stacks.map { it.last() }.joinToString("")
    }

    override fun star2(): String {
        val (stacks, instructions) = input()
        instructions.forEach {
            stacks[it[2]-1].addAll(stacks[it[1]-1].takeLast(it[0]))
            for (i in 0 until it[0]) {
                stacks[it[1]-1].removeLast()
            }
        }
        return stacks.map { it.last() }.joinToString("")
    }

    private fun input(): Pair<List<ArrayList<Char>>, List<List<Int>>> {
        val input = Input.get(5).split("\n\n")
        return input[0]
            .split("\n")
            .dropLast(1)
            .reversed()
            .map { it.toList().filterIndexed { i, _ -> (i % 4) == 1 } }
            .transpose()
            .map { it.dropLastWhile { it.isWhitespace() } as ArrayList } to
                input[1]
                    .split("\n")
                    .dropLast(1)
                    .map { it
                        .split("move ", " from ", " to ")
                        .drop(1)
                        .map { it.toInt() }
                    }
    }

    private fun <T> List<List<T>>.transpose(): List<List<T>> {
        val cols = this[0].size
        val rows = this.size
        return List(cols) { j ->
            List(rows) { i ->
                this[i][j]
            }
        }
    }
}