import december.*

fun main() {
    val riddle: December<String, String> = December5()
    println("Answer 1: " + riddle.star1())
    println("Answer 2: " + riddle.star2())
}
