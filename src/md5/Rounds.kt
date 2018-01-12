import kotlin.math.pow

class Rounds {
    var A: Long = 0x67452301
    var B: Long = 0xefcdab89
    var C: Long = 0x98badcfe
    var D: Long = 0x10325476

    val maxVal: Long = (2.0.pow(32)).toLong()

    fun encrypt(message: LongArray): IntArray {


        var tmpB: Long

        var a: Long = A
        var b: Long = B
        var c: Long = C
        var d: Long = D

        for (k in 0 until (message.size / 16)) {
            val M = LongArray(16, { j -> message[k * 16 + j] })

            for (i in 0..63) {

                tmpB = when {
                    i < 16 -> (a + F(b, c, d) + M[i] + Tables.K[i]) % maxVal
                    i < 32 -> (a + G(b, c, d) + M[(1 + i * 5) % 16] + Tables.K[i]) % maxVal
                    i < 48 -> (a + H(b, c, d) + M[(5 + i * 3) % 16] + Tables.K[i]) % maxVal
                    else -> (a + I(b, c, d) + M[(i * 7) % 16] + Tables.K[i]) % maxVal
                }

                a = d
                d = c
                c = b
                b = (b + shift(tmpB, Tables.lshift[i])) % maxVal

            }

            A = (A + a) % maxVal
            B = (B + b) % maxVal
            C = (C + c) % maxVal
            D = (D + d) % maxVal
        }
        return longsToBytesArray(A, B, C, D)
    }

    private fun shift(n: Long, s: Int): Long {
        var result = n
        for (i in 0 until s) {
            result *= 2
            if (result >= maxVal) {
                result = (result + 1) % maxVal
            }
        }
        return result
    }

    private fun longsToBytesArray(a: Long, b: Long, c: Long, d: Long): IntArray {
        val entry = longArrayOf(a, b, c, d)
        val result = IntArray(16)

        for (i in 0..3) {
            var N = entry[i]
            for (j in 0..3) {
                result[i * 4 + j] = (N % 256).toInt()
                N /= 256
            }
        }
        return result
    }

    fun F(b: Long, c: Long, d: Long): Long {
        return ((b and c) or (invPos(b) and d))
    }

    fun G(b: Long, c: Long, d: Long): Long {
        return (b and d) or (c and invPos(d))
    }

    fun H(b: Long, c: Long, d: Long): Long {
        return b xor c xor d
    }

    fun I(b: Long, c: Long, d: Long): Long {
        return (c xor (b or invPos(d)))
    }

    //Assure that the number is  positive
    fun invPos(d: Long): Long {
        var res = d.inv()
        if (res < 0) {
            res += maxVal
        }
        return res
    }
}