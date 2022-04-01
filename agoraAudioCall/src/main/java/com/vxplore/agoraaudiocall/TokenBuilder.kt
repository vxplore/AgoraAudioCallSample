package com.vxplore.agoraaudiocall

import com.vxplore.agoraaudiocall.tokener.media.RtcTokenBuilder

interface TokenBuilder {
    fun buildTokenWithUid(
        appId: String,
        appCertificate: String,
        channelName: String,
        uid: Int,
        role: RtcTokenBuilder.Role,
        privilegeTs: Int
    ): String
}