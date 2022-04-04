package com.vxplore.agoraaudiocall

import com.vxplore.agoraaudiocall.model.AgoraAppCredential
import com.vxplore.agoraaudiocall.model.MeetTimings

interface Credential {
    val userUid: Int
    val peerUid: Int
    suspend fun getChannelId(): String
    suspend fun meetTimings(): MeetTimings
    suspend fun getAgoraAppCredential(): AgoraAppCredential
}