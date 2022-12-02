package december

import util.Input

class December1 : December<Int, Int> {
    override fun star1(): Int = input().max()

    override fun star2(): Int = input().sortedDescending().take(3).sum()

    private fun input(): List<Int> =
        Input.get(1)
        .split("\n\n")
        .map { it
            .split("\n")
            .dropLastWhile { it.isEmpty() }
            .sumOf { s -> s.toInt() }
        }
        .toList()
}