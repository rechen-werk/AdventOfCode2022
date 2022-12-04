package december

import util.Input

class December4 : December<Int, Int> {
    override fun star1(): Int = Input
        .lines(4)
        .map { line -> line.split("[,-]".toRegex()).map { it.toInt() } }
        .count { it[0] <= it[2] && it[1] >= it[3] || it[2] <= it[0] && it[3] >= it[1] }

    override fun star2(): Int = Input
        .lines(4)
        .map { line -> line.split("[,-]".toRegex()).map { it.toInt() } }
        .count { !(it[1] < it[2] || it[3] < it[0]) }
}