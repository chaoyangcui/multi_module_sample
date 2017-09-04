package singleton

/**
 * Created by IntelliJ IDEA.
 * @author Eric
 * Date    2017/9/1 10:01
 * Desc    Setting | Editor | File and Code Templates
 *
 */
class Singleton private constructor() {
    companion object {
        val instance: Singleton by lazy { Singleton() }
    }

    fun getValue(key: String): String {
        return key + ":val"
    }
}

fun main(args: Array<String>) {
    // Kotlin 中调用
    val key = "Hello"
    Singleton.instance.getValue(key)
}
