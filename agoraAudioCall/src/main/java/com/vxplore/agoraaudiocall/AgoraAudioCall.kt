package com.vxplore.agoraaudiocall

import android.app.Application

interface AgoraAudioCall {
    suspend fun create(callback: AgoraAudioCallback)
    suspend fun joinChannel()
    fun leaveChannel()
    fun destroy()
    fun mute(yes: Boolean)
    fun muteLocal(yes: Boolean)
    fun enabledRemote(yes: Boolean)
    fun toggleLocal()
}