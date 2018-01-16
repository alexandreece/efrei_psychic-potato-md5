fun MD5(msg: String): String {
    val init = Initializer()

    // Array M composed of 32bits words
    val tableMsg = bytesTo32bitsArray(init.appendMessage(msg))

    return compute(tableMsg)

}

fun compute(msg : LongArray): String {
    val hash = Rounds().encrypt(msg)
    return bytesToHexa(hash)
}

fun MD5hexa(msgHexa : String): String{
    val msg = LongArray(msgHexa.length / 8, {i -> hexaToLong(msgHexa.substring(i * 8, (i + 1) * 8))})
    return compute(msg)
}

