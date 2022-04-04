package com.vxplore.agoraaudiocall

import android.Manifest
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hellomydoc.videocall.navigation.RouteNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AudioCalViewModel @Inject constructor(
    private val routeNavigator: RouteNavigator,
    private val agoraAudioCall: AgoraAudioCall,
) : LifeCycleAwareDelegate, ViewModel(), RouteNavigator by routeNavigator {

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
            })
            agoraAudioCall.joinChannel()
        }
    }

    fun callEnd() {
        agoraAudioCall.leaveChannel()
        destroy()
    }

    fun destroy(){
        agoraAudioCall.destroy()
        _leave.value = true
    }

    fun onBackPressed() {
        callEnd()
    }

    fun callMuteLocal() {
        agoraAudioCall.muteLocal(true)
    }
}