import kotlin.experimental.xor

//HMAC for key of type string of characters
fun HMAC(message : String, key : String, verbose : Boolean = false): String {
    validateArgs(message, key)
    var byteKey = ByteArray(key.length, {i -> key[i].toByte()})
    //Reduce key if necessary
    if(key.length > 64){
        byteKey = MD5(byteKey)
    }

    if(verbose){
        println("Message : ")
        println(message)
        println("Key : ")
        println(key)
    }

    return HMAC(message, byteKey, verbose)
}

//HMAC for key of type ByteArray
fun HMAC(message : String, key : ByteArray, verbose : Boolean = false): String {

    //Déclaring computing variables
    val byteMsg = ByteArray(message.length, {i -> message[i].toByte()})
    val padKey = padKey(key)
    val ipad = ByteArray(padKey.size, {0x36.toByte()})
    val opad = ByteArray(padKey.size, {0x5c.toByte()})


    //First step of computing
    val kipad = ByteArray(padKey.size, {i -> padKey[i] xor ipad[i]})
    val kopad = ByteArray(padKey.size, {i -> padKey[i] xor opad[i]})


    //First hashing
    val toHash = concatenate(kipad, byteMsg)
    val hash1 = MD5(toHash)
    //Converting hash to ByteArray

    //Second hashing
    val toHash2 = concatenate(kopad, hash1)
    val hmac = MD5(toHash2)
    val strHmac = byteArrayToString(hmac)

    if(verbose) {
        println("Key :")
        println(byteArrayToString(padKey))
        println("Message : ")
        println(byteArrayToString(byteMsg))
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


fun validateArgs(message : String, key : String){
    if(message.isNullOrEmpty() || key.isNullOrEmpty()){
        throw IllegalArgumentException()
    }
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