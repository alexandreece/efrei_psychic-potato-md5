class HMAC(message : String, key : String){
    val key = appendKey(key)

    //Return a 64 bytes key by appending zeros
    private fun appendKey(key : String): String {
        val sb = StringBuilder(key)

        for(i in key.length until 64){
            sb.append("0")
        }
        return sb.toString()
    }







}

//Etape 1,