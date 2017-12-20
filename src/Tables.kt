import kotlin.math.abs
import kotlin.math.pow
import kotlin.math.sin

class Tables{
    companion object {
        val lshift = intArrayOf(
                7, 12, 17, 22,  7, 12, 17, 22,  7, 12, 17, 22,  7, 12, 17, 22,
                5,  9, 14, 20,  5,  9, 14, 20,  5,  9, 14, 20,  5,  9, 14, 20,
                4, 11, 16, 23,  4, 11, 16, 23,  4, 11, 16, 23,  4, 11, 16, 23,
                6, 10, 15, 21,  6, 10, 15, 21,  6, 10, 15, 21,  6, 10, 15, 21
        )

        val K = LongArray(64, { i -> ((abs(sin(i.toDouble() + 1)) * 4294967296) % 2.0.pow(32)).toLong()})
    }
}