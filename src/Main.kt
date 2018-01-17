fun main(args: Array<String>) {

//Message
    val msg = "Hi There"
    val key = "0b0b0b0b0b0b0b0b0b0b0b0b0b0b0b0b"

    val hmac = HMAC(msg, key, true)
    println("HMAC : ")
    println(hmac)


}

fun testMD5(){

    //Message
    val msg = "Hello World!"


    println("Message :")
    println(msg)


    val hash = MD5(msg)
    println("Hash MD5 : ")
    println(hash)
}