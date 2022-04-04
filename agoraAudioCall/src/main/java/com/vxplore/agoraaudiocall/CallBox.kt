package com.vxplore.agoraaudiocall

import android.app.Activity
import android.content.Intent

class CallBox {
    companion object{
        private var userId = ""
        private var peerId = ""
        private var channelId = ""
        fun start(
            userId: String,
            peerId: String,
            channelId: String,
            activity: Activity
        ){
            this.userId = userId
            this.peerId = peerId
            this.channelId = channelId
            activity.startActivity(Intent(activity,VoiceCallActivity::class.java))
        }

        fun getUserId(): String {
            return this.userId
        }

        fun getPeerId(): String {
            return this.peerId
        }

        fun getChannelId(): String {
            return this.channelId
        }

        fun destroy(){
            this.userId = ""
            this.peerId = ""
            this.channelId = ""
        }
    }
}