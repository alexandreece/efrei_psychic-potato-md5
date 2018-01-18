fun main(args: Array<String>) {
    testHMAC()



}
fun testHMAC(){
    //Message
    val msg = "what do ya want for nothing?"
    val key = "Jefe"

    val hmac = HMAC(msg, key, true)

}
fun testMD5(){
    val msg = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"


    val hash = MD5(msg, true)
    print(hash)
}

fun testhexaToByte() {
    val a = "132A"
    val res = hexaToByte(a.substring(0, 2))
    val res2 = hexaToByte(a.substring(2, 4))


    return
}

fun testhexaStrToByteArray(){
    val a = "132A"
    val res = hexaStrToByteArray(a)

    val h = "45A0234B4707FE701"
    val hh = hexaStrToByteArray(h)
    val hhh = byteArrayToString(hh)
    return
}