package december

import util.Input
import java.lang.IllegalArgumentException

class December3 : December<Int, Int> {
    override fun star1(): Int = Input
        .lines(3)
        .sumOf {
        mapPriority(it
            .substring(0, it.length/2).toSet()
            .intersect(it.substring(it.length/2).toSet())
            .first()
        )
    }

    override fun star2(): Int = Input
        .lines(3)
        .map { it.toSet() }
        .chunked(3)
        .sumOf {
            mapPriority(it[0].intersect(it[1].intersect(it[2])).first())
        }

    private fun mapPriority(common: Char): Int =
        when (common) {
            in 'a'..'z' -> common - 'a' + 1
            in 'A'..'Z' -> common - 'A' + 27
            else -> throw IllegalArgumentException()
        }

}