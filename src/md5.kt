
//MD5 with string of characters as argument
fun MD5(msg: String?): String {
    if(msg.isNullOrEmpty()) {
        return throw IllegalArgumentException()
    }

    val byteMsg = stringToBytes(msg!!)

    val byteHash = MD5(byteMsg)
    val hash = byteArrayToString(byteHash)
    return hash
}


//MD5 with ByteArray as argument
fun MD5(msg : ByteArray): ByteArray {
    //init handle msg formatting
    val init = Initializer()
    val readyMsg = init.appendMessage(msg)

    // Array M composed of 32bits words
    val tableMsg = bytesTo32bitsArray(readyMsg)

    val byteHash = Rounds().encrypt(tableMsg)
    return byteHash

}

