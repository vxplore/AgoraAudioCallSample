package com.vxplore.agoraaudiocall

import android.app.Application

interface AgoraAudioCall {
    suspend fun create(callback: (String,String)->Unit)
    suspend fun joinChannel()
    suspend fun leaveChannel()
    fun destroy()
}