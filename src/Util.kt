//Array of bytes to array of 32 bits words
fun bytesTo32bitsArray(appendMsg: ByteArray): LongArray {
    val result = LongArray(appendMsg.size / 4)
    var sum: Long = 0
    var pow = 1
    for (i in appendMsg.indices) {
        sum += ((appendMsg[i].toLong() + 256) % 256) * pow
        pow *= 256
        if (i % 4 == 3) {
            result[i / 4] = sum
            sum = 0
            pow = 1
        }
    }

    return result
}


//String of hexa numbers as a Long integer
fun hexaToLong(hexa : String): Long {
    var pow = 1
    var result : Long = 0
    for(i in hexa.indices){
        result += Tables.hex.indexOf(hexa[i].toString().toUpperCase()) * pow
        pow *= 16
    }
    return result
}

//From to hexadecimal number of size 2, to byte
fun hexaToByte(hexa : String): Byte {
    return ((Tables.hex.indexOf(hexa[0].toString().toUpperCase())) * 16 + (Tables.hex.indexOf(hexa[1].toString().toUpperCase()))).toByte()
}

fun stringToBytes(str : String): ByteArray {
    return ByteArray(str.length, {i -> str[i].toByte()})
}

fun longsToBytesArray(entry: LongArray): ByteArray {
    val result = ByteArray(16)

    for (i in entry.indices) {
        var value = entry[i]
        for (j in 0..3) {
            result[i * 4 + j] = (value % 256).toByte()
            value /= 256
        }
    }
    return result
}


fun byteArrayToString(arr: ByteArray): String {
    val sb = StringBuilder()

    for(b in arr){
        sb.append(intToString(b.toInt()))
    }
    return sb.toString()
}
//Return int represented by an hexadecimal string
fun intToString(i : Int): String {
    val n = (i + 256) % 256
    return Tables.hex[n / 16] + Tables.hex[n % 16]
}

fun longToHexaString(lg : Long): String {
    var result = ""
    var tmp = lg
    while(tmp > 0){
        result = intToString(tmp.toInt() % 256) + result
        tmp /= 256
    }
    return "0x" + result
}


