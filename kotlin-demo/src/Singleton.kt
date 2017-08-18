/**
 * Created by IntelliJ IDEA.
 * Author: Eric
 * Date:   2017/8/18 13:43
 * Desc:
 *
 */

class Singleton private constructor() {
    init {
        println("singleton in Kotlin.")
    }

    companion object {
        var INSTANCE: Singleton = Singleton()
        fun getInstance(): Singleton {
            println("call method getInstance.")
            return INSTANCE
        }
    }
}

val singleton = Singleton.getInstance()

fun main(args: Array<String>) {
    println(Singleton.INSTANCE == singleton)
}