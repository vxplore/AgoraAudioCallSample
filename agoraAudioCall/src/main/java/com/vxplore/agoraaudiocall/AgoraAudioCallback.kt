package com.vxplore.agoraaudiocall

interface AgoraAudioCallback{
    fun onJoinChannel()
    fun onTick(elapsed: Int, left: Int, totalAllowedSeconds: Int)

}