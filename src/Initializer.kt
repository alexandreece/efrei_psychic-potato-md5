class Initializer {

    fun appendMessage(msg : String): IntArray{
        //Size appended to the message in bytes
        val appSize = 64 - ((msg.length + 9) % 64) + 9
        var result = IntArray(msg.length + appSize)
        //Transform string into int array
        for(i in msg.indices){
            result[i] = msg[i].toInt()
        }
        //append padding
        result[msg.length] = 128
        //Append size of the message
        result = appendSize(result, msg.length)
        return result
    }

    //Transform the size of the message(msgSize here) in to bytes that are appended to the message
    private fun appendSize(msg : IntArray, msgLen: Int): IntArray {
        val result = msg
        var rem = msgLen * 8

        for(i in 0..7) {
            if(rem > 0){
                result[msg.size - (8 - i)] = rem % 256
                rem /= 256
            } else break
        }
        return result
    }


    fun bytesTo32bitsArray(appendMsg : IntArray): LongArray {
        val result = LongArray(appendMsg.size / 4)
        var sum : Long = 0
        var pow = 1
        for(i in appendMsg.indices) {
            sum += appendMsg[i].toLong() * pow
            pow *= 256
            if(i % 4 == 3){
                result[i / 4] = sum
                sum = 0
                pow = 1
            }
        }

        return result
    }
}