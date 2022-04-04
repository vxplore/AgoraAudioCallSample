package com.vxplore.agoraaudiocall

import android.content.Context
import android.util.Log
import com.vxplore.agoraaudiocall.model.MeetTimings
import com.vxplore.agoraaudiocall.tokener.Tokener
import io.agora.rtc.*
import io.agora.rtc.audio.AudioParams
import kotlinx.coroutines.*
import javax.inject.Inject

class AgoraAudioCallImpl @Inject constructor(
    private val credential: Credential,
    private val tokener: Tokener,
    private val application: Context
): AgoraAudioCall {
    var elapsed = -1
    var totalAllowedSeconds: Int = 0
    var meetTimings: MeetTimings? = null
    var timerJob: Job? = null
    private var callback: AgoraAudioCallback? = null
    private var mRtcEngine: RtcEngine? = null
    private val mRtcEventHandler: IRtcEngineEventHandler = object: IRtcEngineEventHandler() {
        override fun onError(err: Int) {
            super.onError(err)
            onEvent("jflflfsdjff","error=$err")
        }

        override fun onJoinChannelSuccess(channel: String?, uid: Int, elapsed: Int) {
            super.onJoinChannelSuccess(channel, uid, elapsed)
            onEvent("jflflfsdjff","Channel Joined=$uid,$elapsed")
            callback?.onJoinChannel()
            startTimer()
        }

        override fun onRejoinChannelSuccess(channel: String?, uid: Int, elapsed: Int) {
            super.onRejoinChannelSuccess(channel, uid, elapsed)
            onEvent("jflflfsdjff","rejoined=$channel,$uid,$elapsed")
        }

        override fun onLeaveChannel(stats: RtcStats?) {
            super.onLeaveChannel(stats)
            onEvent("jflflfsdjff","onLeave")
        }

        override fun onUserJoined(uid: Int, elapsed: Int) {
            super.onUserJoined(uid, elapsed)
            onEvent("jflflfsdjff","onUserJoined=$uid,$elapsed")
        }

        override fun onUserOffline(uid: Int, reason: Int) {
            super.onUserOffline(uid, reason)
            onEvent("jflflfsdjff","onUserOffline=$uid,$reason")
        }

        override fun onConnectionStateChanged(state: Int, reason: Int) {
            super.onConnectionStateChanged(state, reason)
            onEvent("jflflfsdjff","connection=$state,$reason")
        }

        override fun onConnectionInterrupted() {
            super.onConnectionInterrupted()
        }

        override fun onConnectionLost() {
            super.onConnectionLost()
            onEvent("jflflfsdjff","onConnectionLost")
        }

        override fun onConnectionBanned() {
            super.onConnectionBanned()
        }

        override fun onApiCallExecuted(error: Int, api: String?, result: String?) {
            super.onApiCallExecuted(error, api, result)
            onEvent("jflflfsdjff","onApiCallExecuted=$api,$result")
        }

        override fun onTokenPrivilegeWillExpire(token: String?) {
            super.onTokenPrivilegeWillExpire(token)
            onEvent("jflflfsdjff","onTokenPrivilegeWillExpire=$token")
        }

        override fun onMicrophoneEnabled(enabled: Boolean) {
            super.onMicrophoneEnabled(enabled)
        }

        override fun onAudioVolumeIndication(
            speakers: Array<out AudioVolumeInfo>?,
            totalVolume: Int
        ) {
            super.onAudioVolumeIndication(speakers, totalVolume)
            onEvent("jflflfsdjff","onAudioVolumeIndication=$totalVolume")
        }

        override fun onActiveSpeaker(uid: Int) {
            super.onActiveSpeaker(uid)
            onEvent("jflflfsdjff","onActiveSpeaker")
        }

        override fun onFirstLocalAudioFrame(elapsed: Int) {
            super.onFirstLocalAudioFrame(elapsed)
        }

        override fun onFirstLocalAudioFramePublished(elapsed: Int) {
            super.onFirstLocalAudioFramePublished(elapsed)
            onEvent("jflflfsdjff","onFirstLocalAudioFramePublished")
        }

        override fun onFirstRemoteAudioFrame(uid: Int, elapsed: Int) {
            super.onFirstRemoteAudioFrame(uid, elapsed)
        }

        override fun onUserMuteAudio(uid: Int, muted: Boolean) {
            super.onUserMuteAudio(uid, muted)
            onEvent("jflflfsdjff_mute","onUserMuteAudio=$uid,$muted")
        }

        override fun onRemoteAudioStateChanged(uid: Int, state: Int, reason: Int, elapsed: Int) {
            super.onRemoteAudioStateChanged(uid, state, reason, elapsed)
            onEvent("jflflfsdjff","onRemoteAudioStateChanged=$uid,$state,$reason,$elapsed")
        }

        override fun onAudioPublishStateChanged(
            channel: String?,
            oldState: Int,
            newState: Int,
            elapseSinceLastState: Int
        ) {
            super.onAudioPublishStateChanged(channel, oldState, newState, elapseSinceLastState)
            onEvent("jflflfsdjff","onAudioPublishStateChanged=$channel,$oldState,$newState,$elapseSinceLastState")
        }

        override fun onAudioSubscribeStateChanged(
            channel: String?,
            uid: Int,
            oldState: Int,
            newState: Int,
            elapseSinceLastState: Int
        ) {
            super.onAudioSubscribeStateChanged(
                channel,
                uid,
                oldState,
                newState,
                elapseSinceLastState
            )
            onEvent("jflflfsdjff","onAudioSubscribeStateChanged=$channel,$oldState,$newState,$elapseSinceLastState")
        }

        override fun onSnapshotTaken(
            channel: String?,
            uid: Int,
            filePath: String?,
            width: Int,
            height: Int,
            errCode: Int
        ) {
            super.onSnapshotTaken(channel, uid, filePath, width, height, errCode)
            onEvent("jflflfsdjff","onAudioSubscribeStateChanged=$channel,$uid,$filePath,$width,$height,$errCode")
        }

        override fun onChannelMediaRelayStateChanged(state: Int, code: Int) {
            super.onChannelMediaRelayStateChanged(state, code)
            onEvent("jflflfsdjff","onChannelMediaRelayStateChanged=$state,$code")
        }

        override fun onChannelMediaRelayEvent(code: Int) {
            super.onChannelMediaRelayEvent(code)
            onEvent("jflflfsdjff","onChannelMediaRelayEvent=$code")
        }

        override fun onAudioRouteChanged(routing: Int) {
            super.onAudioRouteChanged(routing)
            onEvent("jflflfsdjff","onAudioRouteChanged=$routing")
        }

        override fun onAudioQuality(uid: Int, quality: Int, delay: Short, lost: Short) {
            super.onAudioQuality(uid, quality, delay, lost)
        }

        override fun onRtcStats(stats: RtcStats?) {
            super.onRtcStats(stats)
            stats?.totalDuration
            Log.d("rtcStats",stats?.totalDuration?.toString()?:"null")
        }

        override fun onLastmileQuality(quality: Int) {
            super.onLastmileQuality(quality)
            onEvent("jflflfsdjff","onLastmileQuality=$quality")
        }

        override fun onNetworkQuality(uid: Int, txQuality: Int, rxQuality: Int) {
            super.onNetworkQuality(uid, txQuality, rxQuality)
            onEvent("jflflfsdjff","onNetworkQuality=$uid,$txQuality,$rxQuality")
        }

        override fun onLocalAudioStats(stats: LocalAudioStats?) {
            super.onLocalAudioStats(stats)
            onEvent("jflflfsdjff_audio","onLocalAudioStats=${stats?.sentBitrate}")
        }

        override fun onRemoteAudioStats(stats: RemoteAudioStats?) {
            super.onRemoteAudioStats(stats)
            onEvent("jflflfsdjff","onRemoteAudioStats=$stats")
        }

        override fun onRemoteAudioTransportStats(uid: Int, delay: Int, lost: Int, rxKBitRate: Int) {
            super.onRemoteAudioTransportStats(uid, delay, lost, rxKBitRate)
        }

        override fun onAudioMixingStateChanged(state: Int, reason: Int) {
            super.onAudioMixingStateChanged(state, reason)
            onEvent("jflflfsdjff","onRemoteAudioStats=$state,$reason")
        }

        override fun onAudioMixingFinished() {
            super.onAudioMixingFinished()
        }

        override fun onAudioEffectFinished(soundId: Int) {
            super.onAudioEffectFinished(soundId)
            onEvent("jflflfsdjff","onAudioEffectFinished=$soundId")
        }

        override fun onFirstRemoteAudioDecoded(uid: Int, elapsed: Int) {
            super.onFirstRemoteAudioDecoded(uid, elapsed)
        }

        override fun onLocalAudioStateChanged(state: Int, error: Int) {
            super.onLocalAudioStateChanged(state, error)
            onEvent("jflflfsdjff_audio_stream","onAudioEffectFinished=$state,$error")
        }

        override fun onRequestAudioFileInfo(info: AudioFileInfo?, error: Int) {
            super.onRequestAudioFileInfo(info, error)
        }

        override fun onRtmpStreamingStateChanged(url: String?, state: Int, errCode: Int) {
            super.onRtmpStreamingStateChanged(url, state, errCode)
        }

        override fun onStreamPublished(url: String?, error: Int) {
            super.onStreamPublished(url, error)
        }

        override fun onStreamUnpublished(url: String?) {
            super.onStreamUnpublished(url)
        }

        override fun onStreamMessage(uid: Int, streamId: Int, data: ByteArray?) {
            super.onStreamMessage(uid, streamId, data)
            onEvent("jflflfsdjff","onStreamMessage=$uid,$streamId,$data")
        }

        override fun onStreamMessageError(
            uid: Int,
            streamId: Int,
            error: Int,
            missed: Int,
            cached: Int
        ) {
            super.onStreamMessageError(uid, streamId, error, missed, cached)
            onEvent("jflflfsdjff","onStreamMessageError=$uid,$streamId,$error,$missed,$cached")
        }

        override fun onMediaEngineLoadSuccess() {
            super.onMediaEngineLoadSuccess()
            onEvent("jflflfsdjff","onMediaEngineLoadSuccess")
        }

        override fun onMediaEngineStartCallSuccess() {
            super.onMediaEngineStartCallSuccess()
            onEvent("jflflfsdjff","onMediaEngineStartCallSuccess")
        }

        override fun onNetworkTypeChanged(type: Int) {
            super.onNetworkTypeChanged(type)
            onEvent("jflflfsdjff","onNetworkTypeChanged=$type")
        }
    }

    private fun startTimer() {
        timerJob = CoroutineScope(Dispatchers.IO).launch{
            while (true){
                tick()
                delay(1000)
            }
        }
    }

    private fun tick(){
        ++elapsed
        val left = totalAllowedSeconds - elapsed
        callback?.onTick(elapsed,left,totalAllowedSeconds)
    }

    private fun onEvent(s: String, s1: String) {
        Log.d(s,s1)
        //callback?(s,s1)
    }

    override suspend fun create(callback: AgoraAudioCallback){
        this.callback = callback
        initializeEngine(application)
    }

    override suspend fun joinChannel(){
        meetTimings = credential.meetTimings()
        totalAllowedSeconds = ((meetTimings?.timeSpanMillis?:0)/1000).toInt()
        val token = tokener.new()
        mRtcEngine?.enableAudioVolumeIndication(2000,3,true)
        mRtcEngine?.setChannelProfile(Constants.CHANNEL_PROFILE_COMMUNICATION)
        val channelId = credential.getChannelId()
        val s = mRtcEngine?.joinChannel(token, channelId, null, credential.userUid)
        onEvent("jflflfsdjff","channel join = $s")
    }

    override fun leaveChannel() {
        mRtcEngine?.leaveChannel()
    }

    private suspend fun initializeEngine(application: Context) {
        try {
            val agoraAppCredential = credential.getAgoraAppCredential()
            val appId = agoraAppCredential.appId
            mRtcEngine = RtcEngine.create(application, appId, mRtcEventHandler)
        } catch (e: Exception) {
            onRtcEngineCreationError(e)
        }
    }

    override fun destroy(){
        RtcEngine.destroy()
        mRtcEngine = null
        timerJob?.cancel()
        timerJob = null
        CallBox.destroy()
    }

    override fun mute(yes: Boolean) {
        if(yes){
            mRtcEngine?.disableAudio()
        }
        else{
            mRtcEngine?.enableAudio()
        }
    }

    override fun muteLocal(yes: Boolean) {
        val success = mRtcEngine?.muteLocalAudioStream(yes)
    }

    override fun muteRemote(yes: Boolean) {
        mRtcEngine?.muteRemoteAudioStream(credential.peerUid,yes)
    }

    /////////////////////////////////////////////////////////
    private fun onRtcEngineCreationError(e: Exception) {

    }
}