fun main(args: Array<String>) {
    //Message
    val msg: String?
    val key: String?
    var verbose = false
    var invalid = true

    while (invalid) {
        println("Would you like extended display of the HMAC algorithm? Enter \"yes\" or \"no\".")
        val value = readLine()
        if (!value.isNullOrEmpty()) {
            if (value!!.trim().toUpperCase().equals("YES")) {
                verbose = true
            }
            invalid = false
        }
    }

    println("Enter a message like \"Hello World!\" or nothing for default message : ")
    msg = readLine()
    println("Enter a key like \"Hello World!\" or \"0xA1210CC8E\" or nothing for default message : ")
    key = readLine()

    val hmac = HMAC(msg, key, verbose)

    println("\nHMAC hash : ")
    println(hmac)
}
