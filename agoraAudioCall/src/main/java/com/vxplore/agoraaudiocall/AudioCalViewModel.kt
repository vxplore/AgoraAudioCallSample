package com.vxplore.agoraaudiocall

import android.Manifest
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hellomydoc.videocall.navigation.RouteNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AudioCalViewModel @Inject constructor(
    private val routeNavigator: RouteNavigator,
    private val agoraAudioCall: AgoraAudioCall,
) : LifeCycleAwareDelegate, ViewModel(), RouteNavigator by routeNavigator {

    private val _remoteAudio = mutableStateOf(true)
    val remoteAudio: State<Boolean> = _remoteAudio

    private val _bytes = mutableStateOf(byteArrayOf())
    val bytes: State<ByteArray> = _bytes

    private val _localAudioState = mutableStateOf(false)
    val localAudioState: State<Boolean> = _localAudioState

    private val _remoteAudioState = mutableStateOf(true)
    val remoteAudioState: State<Boolean> = _remoteAudioState

    private val _elapsed = mutableStateOf(0)
    val elapsed: State<Int> = _elapsed

    private val _left = mutableStateOf(0)
    val left: State<Int> = _left

    private val _total = mutableStateOf(0)
    val total: State<Int> = _total
    private val _leave = mutableStateOf(false)
    val leave: State<Boolean> = _leave

    val permissions = arrayOf(
        Manifest.permission.RECORD_AUDIO
    )

    init {

    }
    fun setData(data: Any?) {

    }
    override fun onCreate() {

    }

    override fun onStart() {

    }

    override fun onResume() {

    }

    override fun onPause(isChangingConfiguration: Boolean) {

    }

    override fun onStop() {

    }

    override fun onDestroy(isChangingConfiguration: Boolean) {

    }

    override fun onAnyLifecycleEvent(event: Lifecycle.Event) {

    }

    fun start() {
        viewModelScope.launch {
            agoraAudioCall.create(object : AgoraAudioCallback{
                override fun onJoinChannel() {
                }

                override fun onTick(elapsed: Int, left: Int, totalAllowedSeconds: Int) {
                    _elapsed.value = elapsed
                    _left.value = left
                    _total.value = totalAllowedSeconds
                }

                override fun onLocalAudioStateChanged(b: Boolean) {
                    _localAudioState.value = b
                }

                override fun onTokenExpired() {
                    callEnd()
                }

                override fun onSamples(arr: ByteArray) {
                    this@AudioCalViewModel._bytes.value = arr
                }

                override fun onRemoteAudioMute(muted: Boolean) {
                    _remoteAudio.value = !muted
                }
            })
            agoraAudioCall.joinChannel()
        }
    }

    fun callEnd() {
        viewModelScope.launch {
            agoraAudioCall.leaveChannel()
            destroy()
        }
    }

    fun destroy(){
        agoraAudioCall.destroy()
        _leave.value = true
    }

    fun onBackPressed() {
        callEnd()
    }

    fun callToggleLocal() {
        agoraAudioCall.toggleLocal()
    }

    fun toggleRemoteAudio() {
        _remoteAudioState.value = !_remoteAudioState.value
        agoraAudioCall.enabledRemote(_remoteAudioState.value)
    }
}