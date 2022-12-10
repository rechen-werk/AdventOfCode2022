package december

import util.Input
import java.lang.StringBuilder

class December10 : December<Int, String> {
    override fun star1(): Int = Input
        .lines(10)
        .flatMap { it.split(" ") }
        .foldIndexed(Pair(1, 0)) {idx, regX, cur ->
            val sig = if(listOf(20, 60, 100, 140, 180, 220).contains(idx+1)) {
                (idx+1) * regX.first
            } else 0
            when (cur) {
                "noop", "addx" -> Pair(regX.first, regX.second + sig)
                else -> Pair(regX.first + cur.toInt(), regX.second + sig)
            }
        }.second

    override fun star2(): String {
        var regX = 1
        return Input
            .lines(10)
            .flatMap { it.split(" ") }
            .map() { cur ->
                when (cur) {
                    "noop", "addx" -> regX
                    else -> {
                        val temp = regX
                        regX += cur.toInt()
                        temp
                    }
                }
            }.map { (it-1).rangeTo(it+1) }
            .foldIndexed(StringBuilder("\n")) { idx, screen, current ->
                screen
                    .append(if (current.contains(idx%40)) "#" else " ") //draw pixels
                    .append(if(idx%40==39) "\n" else "") //draw newlines
            }.toString()
    }
}