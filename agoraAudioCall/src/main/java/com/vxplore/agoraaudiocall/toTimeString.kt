package com.vxplore.agoraaudiocall

val Int.toTimeString: String
    get() {
        val m = this/60
        val s = this%60
        val ms = if(m<10) "0$m" else "$m"
        val ss = if(s<10) "0$s" else "$s"
        return "$ms:$ss"
    }