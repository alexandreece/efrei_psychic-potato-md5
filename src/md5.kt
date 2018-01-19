
//MD5 with string of characters as argument
fun MD5(msg: String?, verbose : Boolean = false): String {
    if(msg.isNullOrEmpty()) {
        return throw IllegalArgumentException()
    }
    val byteMsg = stringToBytes(msg!!)


    if(verbose){
        println("Message : ")
        println(msg)
        println("Bytes message : ")
        println(byteArrayToString(byteMsg))
    }
    val byteHash = MD5(byteMsg, verbose)
    val hash = byteArrayToString(byteHash)

    if (verbose) {
        println("Hash : ")
        println(hash)
    }
    return hash
}


//MD5 with ByteArray as argument
fun MD5(msg : ByteArray, verbose : Boolean = false): ByteArray {
    //init handle msg formatting
    val init = Initializer()
    val padMsg = init.padMessage(msg)

    if (verbose) {
        println("Padded message : ")
        println(byteArrayToString(padMsg))
    }

    // Array M composed of 32bits words
    val tableMsg = bytesTo32bitsArray(padMsg)

    val byteHash = Rounds(tableMsg, verbose).encrypt()
    return byteHash
}

