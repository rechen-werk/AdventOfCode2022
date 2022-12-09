package december

import util.Input

class December8 : December<Int, Int> {

    override fun star1(): Int {
        val map = Input
            .lines(8)
            .map { it.toList().map { it.digitToInt() } }

        return map.mapIndexed{ row, v->
            v.filterIndexed{col, elem ->
                listOf(
                    (0 until row).maxOfOrNull { map[it][col] } ?: -1,
                    (row + 1 until map.size).maxOfOrNull { map[it][col] } ?: -1,
                    (0 until col).maxOfOrNull { map[row][it] } ?: -1,
                    (col + 1 until map[row].size).maxOfOrNull { map[row][it] } ?: -1
                ).min() < elem
            }.count()
        }.sum()

    }

    override fun star2(): Int {
        val map = Input
            .lines(8)
            .map { it.toList().map { it.digitToInt() } }

        return map.mapIndexed{row, v ->
            v.mapIndexed {col, elem ->
                (row - ((0 until row).reversed().firstOrNull() { map[it][col] >= elem } ?: 0)) *
                (((row + 1 until map.size).firstOrNull() { map[it][col] >= elem } ?: (map.size -1)) - row) *
                (col - ((0 until col).reversed().firstOrNull() { map[row][it] >= elem } ?: 0)) *
                (((col + 1 until map[row].size).firstOrNull() { map[row][it] >= elem } ?: (map[row].size -1)) - col)
            }.max()
        }.max()
    }
}