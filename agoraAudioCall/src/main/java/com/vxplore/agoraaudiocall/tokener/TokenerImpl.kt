package com.vxplore.agoraaudiocall.tokener

import com.vxplore.agoraaudiocall.Credential
import com.vxplore.agoraaudiocall.TokenBuilder
import com.vxplore.agoraaudiocall.tokener.media.RtcTokenBuilder
import javax.inject.Inject


class TokenerImpl @Inject constructor(
    private val credential: Credential,
    private val tokenBuilder: TokenBuilder
): Tokener {
    override suspend fun new(): String {
        ////////////////////////////////////
        val agoraAppCredential = credential.getAgoraAppCredential()
        /////////////////////////////////////
        val appId = agoraAppCredential.appId
        val appCertificate = agoraAppCredential.appCertificate
        /////////////////////////////////////
        val meetTimings = credential.meetTimings()
        /////////////////////////////////////
        val startTime = meetTimings.startTimeMillis
        val timeSpan = meetTimings.timeSpanMillis
        /////////////////////////////////////
        val timestamp = ((startTime  + timeSpan)/ 1000).toInt()
        /////////////////////////////////////
        val channelId = credential.getChannelId()
        /////////////////////////////////////
        val uid = credential.userUid
        /////////////////////////////////////
        val token = tokenBuilder
            .buildTokenWithUid(
                appId,
                appCertificate,
                channelId,
                uid,
                RtcTokenBuilder.Role.Role_Publisher,
                timestamp
            )
        return token
    }

}