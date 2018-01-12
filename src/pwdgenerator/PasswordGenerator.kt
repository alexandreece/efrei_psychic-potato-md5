package pwdgenerator

import java.util.*

fun generatePassword(nb: Int): Map<String, String> {
    val passwords = mutableMapOf<String, String>()
    val random = Random()
    for (i in 1..nb) {
        passwords.put("id" + i, generateRandomString(random, "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz", random.nextInt(100 - 1) + 1))
    }
    return passwords
}

fun generateRandomString(random: Random, characters: String, length: Int): String {
    val text = CharArray(length)
    for (i in 0 until length) {
        text[i] = characters[random.nextInt(characters.length)]
    }
    return String(text)
}