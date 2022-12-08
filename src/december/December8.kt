package december

import util.Input

class December8 : December<Int, Int> {

    override fun star1(): Int {
        val map = Input.lines(8).map { it.toList() }
        var ctr = 0
        for (row in map.indices) {
            for(col in map[row].indices){
                var visible = false
                if(!visible) {
                    var curVis = true
                    for (i in 0 until row) {
                        if(map[i][col] >= map[row][col]) {
                            curVis = false
                        }
                    }
                    visible = curVis
                }
                if(!visible) {
                    var curVis = true
                    for (i in row + 1 until map.size) {
                        if(map[i][col] >= map[row][col]) {
                            curVis = false
                        }
                    }
                    visible = curVis
                }
                if(!visible) {
                    var curVis = true
                    for (i in 0 until col) {
                        if(map[row][i] >= map[row][col]) {
                            curVis = false
                        }
                    }
                    visible = curVis
                }
                if(!visible) {
                    var curVis = true
                    for (i in col + 1 until map[row].size) {
                        if(map[row][i] >= map[row][col]) {
                            curVis = false
                        }
                    }
                    visible = curVis
                }
                if(visible) ctr++
            }
        }
        return ctr
    }

    override fun star2(): Int {
        val map = Input.lines(8).map { it.toList() }
        var score = 0
        for (row in 1 until map.size-1) {
            for(col in 1 until map[row].size-1){
                var curScore = 1
                var blocked = false
                for (i in (0 until row).reversed()) {
                    if(map[i][col] >= map[row][col]) {
                        curScore *= (row-i)
                        blocked = true
                        break
                    }
                }
                if(!blocked) curScore *= (row)
                blocked = false
                for (i in row + 1 until map.size) {
                    if(map[i][col] >= map[row][col]) {
                        curScore *= (i-row)
                        blocked = true
                        break
                    }
                }
                if(!blocked) curScore *= (map.size-row-1)
                blocked = false
                for (i in (0 until col).reversed()) {
                    if(map[row][i] >= map[row][col]) {
                        curScore *= (col-i)
                        blocked = true
                        break
                    }
                }
                if(!blocked) curScore *= (col)
                blocked = false
                for (i in col + 1 until map[row].size) {
                    if(map[row][i] >= map[row][col]) {
                        curScore *= (i-col)
                        blocked = true
                        break
                    }
                }
                if(!blocked) curScore *= (map[row].size-col-1)
                if (score < curScore) {
                    score = curScore
                }
            }
        }
        return score
    }
}