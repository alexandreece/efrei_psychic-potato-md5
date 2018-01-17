import kotlin.experimental.xor

fun HMAC(message : String, key : String, verbose : Boolean = false): String {
    validateArgs(message, key)


    //Déclaring computing variables
    val byteMsg = ByteArray(message.length, {i -> message[i].toByte()})
    val padKey = pad(hexaStrToByteArray(key))
    val ipad = ByteArray(padKey.size, {0x36.toByte()})
    val opad = ByteArray(padKey.size, {0x5c.toByte()})


    //First step of computing
    val kipad = ByteArray(padKey.size, {i -> padKey[i] xor ipad[i]})
    val kopad = ByteArray(padKey.size, {i -> padKey[i] xor opad[i]})


    //First hashing
    val hash1 = MD5(concatenate(kipad, byteMsg))
    //Converting hash to ByteArray

    //Second hashing
    val hmac = MD5(concatenate(kopad, hash1))

    if(verbose) {
        println("Key :")

        println("Message : ")
    }

    if(verbose) {
        println("K xor ipad :")

        println("K xor opad :")

    }

    return byteArrayToString(hmac)
}

//Return a 64 bytes key by appending zeros
private fun padKey(key : String): String {
    return key.padEnd(64, '0')
}

fun validateArgs(message : String, key : String){

    var validArgs = false

    if(!message.isNullOrEmpty()){
        validArgs = true
        for(c in key){
            if(Tables.hex.indexOf(c.toString().toUpperCase()) == -1) validArgs = false
        }
    }
    if(!validArgs) throw IllegalArgumentException()

}

fun concatenate(arr1 : ByteArray, arr2 : ByteArray): ByteArray {
    val result = ByteArray(arr1.size + arr2.size)

    System.arraycopy(arr1, 0, result, 0, arr1.size)
    System.arraycopy(arr2, 0, result, arr1.size, arr2.size)

    return result
}

fun pad(arr : ByteArray): ByteArray {
    val result = ByteArray(64)
    System.arraycopy(arr, 0, result, 0, arr.size)
    return result
}

fun hexaStrToByteArray(hexa : String): ByteArray{
    val size = hexa.length
    return ByteArray(size / 2, {i -> hexaToByte(hexa.substring(size - 2 - i * 2,size - i * 2))})
}