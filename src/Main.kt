fun main(args: Array<String>) {
    println("MD5 TESTER\n")
    var invalid = true
    var vb = false

    while (invalid) {
        println("Would you like extensive display of the algorithm? Enter yes or no(no by default).")
        var line = readLine()!!
        if (!line.isNullOrEmpty()) {
            invalid = false
            if (line.trim().toUpperCase().equals("YES")) vb = true
        }
    }

    println()

    while (true) {
        println("Enter a message like \"Hello World!\" or nothing for default message : ")
        var msg = readLine()
        if (msg.isNullOrEmpty()) msg = "Hello World!"
        val hash = MD5(msg, vb)
        println()
        println("MD5 hash : ")
        println(hash)
        println()
    }
}
