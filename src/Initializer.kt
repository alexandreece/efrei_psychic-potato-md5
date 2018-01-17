class Initializer {

    fun appendMessage(msg: String): IntArray {
        //Size appended to the message in bytes
        val appSize = 64 - ((msg.length + 9) % 64) + 9
        var result = IntArray(msg.length + appSize)
        //Transform string into int array
        for (i in msg.indices) {
            result[i] = msg[i].toInt()
        }
        //append padding
        result[msg.length] = 128
        //Append size of the message
        result = appendSize(result, msg.length)
        return result
    }

    fun appendMessage(msg: ByteArray): IntArray {
        //Size appended to the message in bytes
        val appSize = 64 - ((msg.size + 9) % 64) + 9
        //Transform string into int array
        var result = IntArray(msg.size + appSize)
        for(i in msg.indices){
            result[i] = msg[i].toInt()
        }

        //append padding
        result[msg.size] = 128
        //Append size of the message
        result = appendSize(result, msg.size)
        return result
    }

    //Transform the size of the message(msgSize here) in to bytes that are appended to the message
    private fun appendSize(msg: IntArray, msgLen: Int): IntArray {
        var rem = msgLen * 8

        for (i in 0..7) {
            if (rem > 0) {
                msg[msg.size - (8 - i)] = rem % 256
                rem /= 256
            } else break
        }
        return msg
    }



}