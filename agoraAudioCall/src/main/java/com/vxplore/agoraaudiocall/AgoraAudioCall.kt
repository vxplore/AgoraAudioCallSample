package com.vxplore.agoraaudiocall

import android.app.Application

interface AgoraAudioCall {
    suspend fun create()
    suspend fun joinChannel()
    suspend fun leaveChannel()
    fun destroy()
}