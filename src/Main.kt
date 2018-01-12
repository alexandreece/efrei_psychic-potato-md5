import pwdgenerator.generatePassword
import java.io.File

fun main(args: Array<String>) {
    val passwords = generatePassword(100);
    File("./passwordsPart2.txt").printWriter().use { out ->
        passwords.forEach {
            val hash = MD5(it.value)
            out.println("${it.key} -> ${it.value} -> MD5: ${hash}")
        }
    }
}