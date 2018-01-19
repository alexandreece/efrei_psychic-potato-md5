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

//From String of chars to ByteArray
fun stringToBytes(str : String): ByteArray {
    return ByteArray(str.length, {i -> str[i].toByte()})
}

//From table of 16 longs of 32bits to ByteArray
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

//From ByteArray to hexadecimal string
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

//From long to string to be displayed on 8 hexadecimal figures
fun longToHexa8(l: Long): String {
    var result = ""
    var value = l
    for (i in 0..7) {
        result = Tables.hex[(value % 16).toInt()] + result
        value /= 16
    }
    return "0x" + result
}
