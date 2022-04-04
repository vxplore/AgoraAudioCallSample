package com.vxplore.agoraaudiocall

import com.vxplore.agoraaudiocall.model.AgoraAppCredential
import com.vxplore.agoraaudiocall.model.MeetTimings
import javax.security.auth.callback.Callback

class CredentialMockImpl: Credential {

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
            startTimeMillis = System.currentTimeMillis(),
            timeSpanMillis = 15*60*1000
        )
    }

    override suspend fun getAgoraAppCredential(): AgoraAppCredential {
        return AgoraAppCredential(
            appId = "13622905826d46538beb9bd1d96c83b1",
            appCertificate = "a7138949c72440cd81607fc3edcb3f01"
        )
    }
}