package december

import util.Input

class December2 : December<Int, Int> {
    private val LOSS = 0
    private val DRAW = 3
    private val WIN = 6
    private val ROCK = 1
    private val PAPER = 2
    private val SCISSORS = 3

    override fun star1(): Int =
        Input.lines(2).sumOf {
            val split = it.split(" ")
            calculatePointsOnGuess(rps(split[0]), rps(split[1]))
        }

    override fun star2(): Int =
        Input.lines(2).sumOf {
            val split = it.split(" ")
            calculatePointsFromFullInstructions(rps(split[0]), ldw(split[1]))
        }


    private fun calculatePointsOnGuess(player1: Int, advice: Int): Int {
        if (player1 == advice) {
            return DRAW + advice
        }
        if (player1 % 3 == (advice - 1) % 3) {
            return WIN + advice
        }
        if (player1 % 3 == (advice + 1) % 3) {
            return LOSS + advice
        }
        throw IllegalStateException()
    }

    private fun calculatePointsFromFullInstructions(player1: Int, advice: Int): Int =
        when (advice) {
            LOSS -> LOSS + (player1 - 4) % 3 + 3
            DRAW -> DRAW + player1
            WIN  -> WIN + (player1 - 5) % 3 + 3
            else -> throw IllegalStateException()
        }

    private fun rps(abc: String): Int =
        when (abc) {
            "A", "X" -> ROCK
            "B", "Y" -> PAPER
            "C", "Z" -> SCISSORS
            else -> throw IllegalArgumentException(abc)
        }

    private fun ldw(xyz: String): Int =
        when (xyz) {
            "X" -> LOSS
            "Y" -> DRAW
            "Z" -> WIN
            else -> throw IllegalArgumentException(xyz)
        }
}