fun main(args: Array<String>) {
    //Message
    val msg = "Hello World!"
    var msgHexa = "48656c6c6f20576f726c6421"
    msgHexa = msgHexa.toUpperCase().padEnd(128, '0')

    println("Message :")
    println(msg)
    println(msgHexa)

    val hash = MD5(msg)
    val hashHexa = MD5hexa(msgHexa)
    println("Hash MD5 : ")
    println(hash)
    println(hashHexa)
}