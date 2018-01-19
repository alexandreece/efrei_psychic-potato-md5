import pwdgenerator.generatePassword
import java.io.File

fun main(args: Array<String>) {
    val passwords = generatePassword(100)
    File("./passwordsPart3.txt").printWriter().use { out ->
        passwords.forEach {
            val salt = MD5(it.key) //on choisit comme sel le hash de l'id, on l'ajoutera à la fin du password
            val saltedHash = MD5(it.value + salt)
            out.println("${it.key} -> ${it.value} + salt: ${salt} -> salted MD5: ${saltedHash}")
        }
    }
}