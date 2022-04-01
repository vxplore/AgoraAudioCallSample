package com.vxplore.agoraaudiocall

import com.vxplore.agoraaudiocall.model.AgoraAppCredential
import com.vxplore.agoraaudiocall.model.MeetTimings

interface Credential {
    val userUid: Int
    suspend fun getChannelId(): String
    suspend fun meetTimings(): MeetTimings
    suspend fun getAgoraAppCredential(): AgoraAppCredential
}