fun main(args: Array<String>) {
    //Message
    val init = Initializer()
    val msg = "Hello World!"
    println("Message :")
    println(msg)
    // Array M composed of 32bits words
    val tableMsg = init.bytesTo32bitsArray(init.appendMessage(msg))
    println("Table M from the message")
    displayHexArray(toBytesArray(tableMsg))

    val rds = Rounds()
    val hash = rds.encrypt(tableMsg)
    println("Hash MD5 : ")
    displayBytesArray(hash)
}

//Go from 32bits word array to 8bits word array0
fun toBytesArray(arr: LongArray): IntArray {
    val result = IntArray(arr.size * 4)
    for (i in arr.indices) {
        var num = arr[i]
        for (j in 0..3) {
            result[i * 4 + j] = (num % 256).toInt()
            num /= 256
        }

    }
    return result
}

fun displayBytesArray(arr: IntArray) {
    for (i in arr.indices) {
        displayHex(arr[i])
    }
}

fun displayHexArray(M: IntArray) {
    for (i in M.indices) {
        displayHex(M[(i / 4) * 4 + (3 - (i % 4))])
        if (i % 4 == 3) println()
    }

}

fun displayHex(num: Int) {
    val hex = charArrayOf('0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F')
    print(hex[num / 16])
    print(hex[num % 16])
    print(" ")
}