

fun MD5(msg: String?): String {
    if(msg.isNullOrEmpty()) {
        return throw IllegalArgumentException()
    }

    val byteMsg = stringToBytes(msg!!)

    val byteHash = MD5(byteMsg)
    val hash = byteArrayToString(byteHash)
    return hash
}


fun compute(msg : LongArray): ByteArray {
    return Rounds().encrypt(msg)
}

fun MD5(msg : ByteArray): ByteArray {
    //init handle msg formatting
    val init = Initializer()
    val readyMsg = init.appendMessage(msg)

    // Array M composed of 32bits words
    val tableMsg = bytesTo32bitsArray(readyMsg)

    val byteHash = compute(tableMsg)
    return byteHash

}

