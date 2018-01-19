class Initializer {

    fun appendMessage(msg: ByteArray): ByteArray {
        //Size appended to the message in bytes
        val appSize = 64 - ((msg.size + 9) % 64) + 9
        //Transform string into int array
        var result = ByteArray(msg.size + appSize)
        System.arraycopy(msg, 0, result, 0, msg.size)
        //append padding
        result[msg.size] = 128.toByte()
        //Append size of the message
        result = appendSize(result, msg.size)
        return result
    }

    //Transform the size of the message(msgSize here) in to bytes that are appended to the message
    private fun appendSize(msg: ByteArray, msgLen: Int): ByteArray {

        var bitLen = msgLen * 8

        for (i in 0..7) {
            if (bitLen > 0) {
                msg[msg.size - (8 - i)] = (bitLen % 256).toByte()
                bitLen /= 256
            } else break
        }
        return msg
    }


}