fun MD5(msg: String): String {
    val init = Initializer()
    // Array M composed of 32bits words
    val tableMsg = init.bytesTo32bitsArray(init.appendMessage(msg))

    val rds = Rounds()
    val hash = rds.encrypt(tableMsg)

    return toString(hash)

}

fun toString(arr: IntArray): String {
    var str = ""
    for (i in arr.indices) {
        var num = arr[i]
        val hex = arrayOf("0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F")
        str += hex[num / 16]
        str += hex[num % 16]
    }
    return str
}