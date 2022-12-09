package december

import util.Input
import kotlin.math.abs
import kotlin.math.sign

class December9 : December<Int, Int> {
    override fun star1(): Int = moveRope(2)

    override fun star2(): Int = moveRope(10)

    private fun moveRope(size: Int): Int {
        val states = HashSet<Pair<Int, Int>>()
        val snake = MutableList(size) { Pair(0,0) }
        states.add(snake.last())
        Input.lines(9)
            .map { it.split(" ") }
            .forEach { inst ->
                repeat(inst[1].toInt()) {
                    snake.move(dir(inst[0]))
                    states.add(snake.last())}
            }
        return states.size
    }

    private fun MutableList<Pair<Int, Int>>.move(dir: Pair<Int, Int>) {
        this[0] += dir
        (1 until size).forEach{ i ->
            this[i] = this[i] follow this[i-1]
        }
    }

    private fun dir(dir: String): Pair<Int, Int> = when (dir) {
        "U" -> Pair(1, 0)
        "R" -> Pair(0,1)
        "D" -> Pair(-1,0)
        "L" -> Pair(0,-1)
        else -> throw IllegalStateException()
    }

    private operator fun Pair<Int, Int>.plus(other: Pair<Int, Int>): Pair<Int, Int> =
        Pair(first+other.first, second+other.second)

    private infix fun Pair<Int, Int>.follow(other: Pair<Int, Int>): Pair<Int, Int> =
        if(abs(first - other.first) > 1 || abs(second - other.second) > 1)
            this + Pair((other.first-first).sign, (other.second-second).sign)
        else this
}