
//MD5 with string of characters as argument
fun MD5(msg: String?, verbose : Boolean = false): String {
    if(msg.isNullOrEmpty()) {
        return throw IllegalArgumentException()
    }

    val byteMsg = stringToBytes(msg!!)

    if(verbose){
        println("Message : ")
        println(msg)

    }

    val byteHash = MD5(byteMsg, verbose)
    val hash = byteArrayToString(byteHash)
    return hash
}


//MD5 with ByteArray as argument
fun MD5(msg : ByteArray, verbose : Boolean = false): ByteArray {
    //init handle msg formatting
    val init = Initializer()
    val readyMsg = init.appendMessage(msg)

    // Array M composed of 32bits words
    val tableMsg = bytesTo32bitsArray(readyMsg)

    val byteHash = Rounds(tableMsg, verbose).encrypt()
    return byteHash

}

