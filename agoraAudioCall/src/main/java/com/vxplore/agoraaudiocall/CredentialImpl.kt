package com.vxplore.agoraaudiocall

import com.vxplore.agoraaudiocall.model.AgoraAppCredential
import com.vxplore.agoraaudiocall.model.MeetTimings
import javax.inject.Inject
import javax.security.auth.callback.Callback

class CredentialImpl @Inject constructor(
    private val metar: Metar
): Credential {

    override val userUid: Int
        get(){
            val userId = CallBox.getUserId()
            val peerId = CallBox.getPeerId()
            return if(userId>peerId){
                2
            } else{
                1
            }
        }
    override val peerUid: Int
        get(){
            val userId = CallBox.getUserId()
            val peerId = CallBox.getPeerId()
            return if(userId>peerId){
                1
            } else{
                2
            }
        }

    override suspend fun getChannelId(): String {
        return CallBox.getChannelId()
    }

    override suspend fun meetTimings(): MeetTimings {
        return MeetTimings(
            startTimeMillis = CallBox.getStartTime(),
            timeSpanMillis = CallBox.getTotalTime()
        )
    }

    override suspend fun getAgoraAppCredential(): AgoraAppCredential {
        return AgoraAppCredential(
            appId = metar[Constants.APP_ID_KEY],
            appCertificate = metar[Constants.APP_CERTIFICATE_KEY]
        )
    }
}