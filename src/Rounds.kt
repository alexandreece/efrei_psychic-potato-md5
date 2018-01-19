import kotlin.math.pow

class Rounds(msg: LongArray, verbose : Boolean) {
    val message = msg
    val vb = verbose

    var A: Long = 0x67452301
    var B: Long = 0xefcdab89
    var C: Long = 0x98badcfe
    var D: Long = 0x10325476



    val maxVal: Long = (2.0.pow(32)).toLong()

    fun encrypt(): ByteArray {
        if (vb) {
            println("Rounds of encryption, 512bits by 512bits.")
        }

        //tmpB is the variable which is computed
        var tmpB: Long

        for (k in 0 until (message.size / 16)) {
            if (vb) {
                println("Round ${k + 1}, 64 steps : ")
                println("At beginning : ")
                println("A : ${longToHexa8(A)}")
                println("B : ${longToHexa8(B)}")
                println("C : ${longToHexa8(C)}")
                println("D : ${longToHexa8(D)}\n")
            }

            //M represent a 512 bits message part
            val M = LongArray(16, { j -> message[k * 16 + j] })

            //Saving A B C and D
            var a: Long = A
            var b: Long = B
            var c: Long = C
            var d: Long = D

            for (i in 0..63) {
                tmpB = when {
                    i < 16 -> (A + F(B, C, D) + M[i] + Tables.K[i]) % maxVal
                    i < 32 -> (A + G(B, C, D) + M[(1 + i * 5) % 16] + Tables.K[i]) % maxVal
                    i < 48 -> (A + H(B, C, D) + M[(5 + i * 3) % 16] + Tables.K[i]) % maxVal
                    else -> (A + I(B, C, D) + M[(i * 7) % 16] + Tables.K[i]) % maxVal
                }

                A = D
                D = C
                C = B
                B = (B + shift(tmpB, Tables.lshift[i])) % maxVal
                if (vb) {
                    println("Value of B after step ${i + 1} computation : ")
                    println("${longToHexa8(B)}\n")
                }
            }

            A = (A + a) % maxVal
            B = (B + b) % maxVal
            C = (C + c) % maxVal
            D = (D + d) % maxVal

            if (vb) {
                println("End of round ${k + 1} : ")
                println("A : ${longToHexa8(A)}")
                println("B : ${longToHexa8(B)}")
                println("C : ${longToHexa8(C)}")
                println("D : ${longToHexa8(D)}\n")
            }
        }
        val byteHash = longsToBytesArray(longArrayOf(A, B, C, D))
        return byteHash
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