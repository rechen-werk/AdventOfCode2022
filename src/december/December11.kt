package december

import util.Input

class December11 : December<Long, Long> {
    override fun star1(): Long = monke(3, 20)

    override fun star2(): Long = monke(1, 10000)

    private fun monke(relief: Int, repeat: Int): Long {
        Monkey.resetHivemind()
        val monke = Input
            .get(11)
            .split("\n\n")
            .map { it.split("\n").dropLastWhile { it.isEmpty() } }
            .dropLastWhile { it.isEmpty() }
            .map { Monkey(
                it[1]
                    .substring(18)
                    .split(", ")
                    .map { it.toLong() } as MutableList<Long>,
                Monkey.operation(
                    it[2]
                        .substring(19)
                        .split(" "),
                    relief
                ),
                { value: Long ->
                    if(value % it[3].substring(21).toLong() == 0L)
                        it[4].substring(29).toInt()
                    else
                        it[5].substring(30).toInt()
                },
                it[3].substring(21).toLong()
            ) }
        (1..repeat).forEach{ _ -> monke.forEach{ it.inspectItems(monke) } }
        return monke
            .sortedBy { it.monkeyBusiness }
            .takeLast(2)
            .fold(1L) {acc, monkey -> acc * monkey.monkeyBusiness }
    }

    private class Monkey(
        private val items: MutableList<Long>,
        private val operation: ((Long) -> Long),
        private val test: ((Long) -> Int),
        braincell: Long
    ) {
        var monkeyBusiness = 0L
            private set

        init {
            hivemind *= braincell
        }
        companion object {
            private var hivemind = 1L

            fun resetHivemind() {
                hivemind = 1
            }

            fun operation(tokens: List<String>, relief: Int): (Long) -> Long =
                when(val right = tokens[2].toLongOrNull()) {
                    null -> { left: Long -> (left.op(left, tokens[1])) / relief % hivemind}
                    else -> { left: Long -> (left.op(right, tokens[1])) / relief % hivemind }
                }

            private fun Long.op(other: Long, op: String) : Long =
                when(op) {
                   "+" -> this + other
                   "*" -> this * other
                   else ->  throw IllegalStateException()
                }
        }

        private fun receiveItem(item: Long) = items.add(item)

        fun inspectItems(monkeys: List<Monkey>) {
            items.forEach{
                monkeyBusiness++
                monkeys[test(operation(it))].receiveItem(operation(it))
            }
            items.clear()
        }
    }
}