import kotlin.experimental.xor

//HMAC for key of type string of characters
fun HMAC(message: String?, key: String?, verbose: Boolean = false): String {
    val readyMsg: String
    val readyKey: String
    if (message.isNullOrEmpty()) {
        readyMsg = "Hi There"
    } else {
        readyMsg = message!!
    }
    if (key.isNullOrEmpty()) {
        readyKey = "0x0b0b0b0b0b0b0b0b0b0b0b0b0b0b0b0b"
    } else {
        readyKey = key!!
    }

    if(verbose){
        println("Message : ")
        println(readyMsg)
        println("Key : ")
        println(readyKey)
    }

    //Key to ByteArray
    var byteKey = userInputToBytes(readyKey)
    //Message to ByteArray
    var byteMsg = userInputToBytes(readyMsg)
    //Reduce key if necessary
    if (byteKey.size > 64) {
        byteKey = MD5(byteKey)
    }


    return HMAC(byteMsg, byteKey, verbose)
}

fun userInputToBytes(str: String): ByteArray {
    val result: ByteArray

    if (str.substring(0, 2).equals("0x")) {
        val hexaStr = str.substring(2)
        result = hexaStrToByteArray(hexaStr)
    } else {
        result = stringToBytes(str)
    }

    return result
}


//HMAC for arguments of type ByteArray
fun HMAC(message: ByteArray, key: ByteArray, verbose: Boolean = false): String {

    //Déclaring computing variables
    val padKey = padKey(key)
    val ipad = ByteArray(padKey.size, {0x36.toByte()})
    val opad = ByteArray(padKey.size, {0x5c.toByte()})


    //First step of computing
    val kipad = ByteArray(padKey.size, {i -> padKey[i] xor ipad[i]})
    val kopad = ByteArray(padKey.size, {i -> padKey[i] xor opad[i]})


    //First hashing
    val toHash = concatenate(kipad, message)
    val hash1 = MD5(toHash)
    //Converting hash to ByteArray

    //Second hashing
    val toHash2 = concatenate(kopad, hash1)
    val hmac = MD5(toHash2)
    val strHmac = byteArrayToString(hmac)

    if(verbose) {
        println("Message ready : ")
        println(byteArrayToString(message))
        println("Key ready :")
        println(byteArrayToString(padKey))
        println("K xor ipad :")
        println(byteArrayToString(kipad))
        println("Kipad concatenated with message : ")
        println(byteArrayToString(toHash))
        println("K xor opad :")
        println(byteArrayToString(kopad))
        println("Kopad concatenated with the first hash : ")
        println(byteArrayToString(toHash2))
        println("First hash : ")
        println(byteArrayToString(hash1))
        println("HMAC : ")
        println(strHmac)
    }

    return strHmac
}

fun concatenate(arr1 : ByteArray, arr2 : ByteArray): ByteArray {
    val result = ByteArray(arr1.size + arr2.size)

    System.arraycopy(arr1, 0, result, 0, arr1.size)
    System.arraycopy(arr2, 0, result, arr1.size, arr2.size)

    return result
}

fun padKey(arr : ByteArray): ByteArray {
    val result = ByteArray(64)
    System.arraycopy(arr, 0, result, 0, arr.size)
    return result
}

fun hexaStrToByteArray(hexa : String): ByteArray{
    val size = hexa.length
    return ByteArray(size / 2, {i -> hexaToByte(hexa.substring(i * 2, i * 2 + 2))})
}