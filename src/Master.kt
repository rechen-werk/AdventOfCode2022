import december.*

fun main() {
    val riddle: December<out Any, out Any> = December7()
    println("Answer 1: " + riddle.star1())
    println("Answer 2: " + riddle.star2())
}
