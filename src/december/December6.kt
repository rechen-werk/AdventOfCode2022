package december

import util.Input

class December6 : December<Int, Int> {
    companion object {
        private const val WINDOW_1 = 4
        private const val WINDOW_2 = 14
    }

    override fun star1(): Int = WINDOW_1 + Input.get(6)
        .windowed(WINDOW_1,1)
        .indexOfFirst { it.toList().distinct().size == WINDOW_1 }

    override fun star2(): Int = WINDOW_2 + Input.get(6)
        .windowed(WINDOW_2,1)
        .indexOfFirst { it.toList().distinct().size == WINDOW_2 }
}