fun main(args: Array<String>) {
    testHMAC()



}
fun testHMAC(){
    while (true) {

        //Message
        var msg: String?
        var key: String?

        println("Enter a message like \"Hello World!\" or nothing for default message : ")
        msg = readLine()
        println("Enter a key like \"Hello World!\" or \"0xA1210CC8E\" or nothing for default message : ")
        key = readLine()

        val hmac = HMAC(msg, key, true)

    }

}
fun testMD5(){
    val msg = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"


    val hash = MD5(msg, true)
    print(hash)
}
