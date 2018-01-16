//TAble of bytes to String of hexadecimal
fun bytesToHexa(arr: IntArray): String {
    var str = ""
    for (i in arr.indices) {
        val num = arr[i]

        str += Tables.hex[num / 16]
        str += Tables.hex[num % 16]
    }
    return str
}

//String of chars to string of Hexadecimals
private fun charsToHexa(chars : String): String {
    val sb = StringBuilder()

    for(char in chars){
        sb.append(intToHexa(char.toInt()))
    }

    return sb.toString()
}

//Int to hexadecimal string
private fun intToHexa(value : Int): String{
    var value = value
    var result = ""

    while(value > 0){
        result += Tables.hex[value % 16]
        value /= 16
    }

    return result
}

//Array of bytes to array of 32 bits words
fun bytesTo32bitsArray(appendMsg: IntArray): LongArray {
    val result = LongArray(appendMsg.size / 4)
    var sum: Long = 0
    var pow = 1
    for (i in appendMsg.indices) {
        sum += appendMsg[i].toLong() * pow
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
        result += Tables.hex.indexOf(hexa[i].toString()) * pow
        pow *= 16
    }
    return result
}